plugins {
    kotlin(KotlinPlugins.Multiplatform)
    kotlin(KotlinPlugins.Cocoapods)
    id(AndroidPlugins.AndroidLibrary)
    kotlin(KotlinPlugins.Serialization) version Kotlin.Version
    id(AndroidPlugins.Ksp) version AndroidPlugins.KspVersion
    id(SqlDelight.Plugin) version SqlDelight.Version
    id(GradleVersions.Plugin) version GradleVersions.Version
    id(KMPNativeCoroutine.Plugin) version KMPNativeCoroutine.Version
    id(Moko.KswiftPlugin) version Moko.KswiftVersion
}

version = "1.0"

kotlin {
    jvmToolchain(8)
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
//        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        all {
            languageSettings.apply {
                optIn(KMPNativeCoroutine.OptInObjCName)
                optIn(MultiplatformSettings.OptInSettings)
                optIn(MultiplatformSettings.OptInSettingsImpl)
            }
        }
        val commonMain by getting {
            dependencies {
                // SqlDelight
                implementation(SqlDelight.runtime)
                implementation(SqlDelight.coroutineExt)
                // Koin
                api(Koin.core)
                // Ktor
                implementation(Ktor.core)
                implementation(Ktor.logging)
                implementation(Ktor.contentNegotiation)
                implementation(Ktor.serialization)
                implementation(Kermit.kermitLogger)
                // Coroutine
                implementation(Kotlin.coroutineCore)
                // Serialization
                implementation(Kotlin.serializationCore)
                implementation(Kotlin.serializationJson)
                // Kotlin datetime
                implementation(Kotlin.datetime)
                // Multiplatform Settings
                implementation(MultiplatformSettings.mps)
                implementation(MultiplatformSettings.mpsCoroutine)

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(MultiplatformSettings.mpsTest)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(SqlDelight.androidDriver)
                implementation(Ktor.okhttp)
                implementation(AndroidX.datastorePref)
                implementation(MultiplatformSettings.mpsDataStore)
            }
        }
//        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation(SqlDelight.nativeDriver)
                implementation(Ktor.ios)
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

kswift {
    install(dev.icerock.moko.kswift.plugin.feature.SealedToSwiftEnumFeature)
}

sqldelight {
    databases {
        create("WalletlineDB") {
            packageName.set("com.walletline.database")
            sourceFolders.add("sqldelight")
        }
    }
}

android {
    namespace = "com.walletline"
    compileSdk = AndroidApplication.CompileSdkVersion
    defaultConfig {
        minSdk = AndroidApplication.MinSdkVersion
        targetSdk = AndroidApplication.TargetSdkVersion
    }
}

tasks.named<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask>("dependencyUpdates")
    .configure {
        checkForGradleUpdate = true
        outputFormatter = "json"
        outputDir = "build/dependencyUpdates"
        reportfileName = "report"
    }