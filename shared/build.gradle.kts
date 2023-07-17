plugins {
    kotlin(KotlinPlugins.Multiplatform)
    kotlin(KotlinPlugins.Cocoapods)
    id(AndroidPlugins.AndroidLibrary)
    kotlin(KotlinPlugins.Serialization) version Kotlin.Version
    id(AndroidPlugins.Ksp) version AndroidPlugins.KspVersion
    id(SqlDelight.Plugin) version SqlDelight.Version
    id(GradleVersions.Plugin) version GradleVersions.Version
    id(KMPNativeCoroutine.Plugin) version KMPNativeCoroutine.Version
}

version = "1.0"

kotlin {
    jvmToolchain(8)
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Walletline Shared Module"
        homepage = "https://datarivers.org/"
//        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        all {
            languageSettings.apply {
                optIn(KMPNativeCoroutine.OptInObjCName)
                optIn(MultiplatformSettings.OptInSettings)
                optIn(MultiplatformSettings.OptInSettingsImpl)
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
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
                implementation(Kermit.logger)
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

                // Kmm Firebase
                implementation(Firebase.kmmAuth)

                // BigNum
                implementation(BigNum.bigNum)

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(Kotlin.test)
                implementation(MultiplatformSettings.mpsTest)
                implementation(Koin.test)
                implementation(Test.turbine)
                implementation(Kotlin.coroutineTest)
                implementation(Ktor.clientMock)
                implementation(Kotest.assertion)
                implementation(Mockative.core)
                // BigNum
                implementation(BigNum.bigNum)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Koin.test)
                implementation(Kotlin.test)
                implementation(SqlDelight.androidDriver)
                implementation(Ktor.okhttp)
                implementation(AndroidX.datastorePref)
                implementation(MultiplatformSettings.mpsDataStore)
            }
        }
        val androidInstrumentedTest by getting {
            dependencies {
                implementation(AndroidX.junit)
                implementation(Kotlin.coroutineTest)
                implementation(Test.robolectric)
                implementation(SqlDelight.jvmDriver)
                implementation("androidx.test:runner:1.5.2")
                implementation("androidx.test:core:1.5.0")
                implementation("androidx.test.ext:junit:1.1.5")
                implementation("androidx.test:rules:1.5.0")
            }
        }
        val androidUnitTest by getting {
            dependencies {
                dependsOn(androidInstrumentedTest)
            }
        }
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



sqldelight {
    databases {
        create("WalletlineDB") {
            packageName.set("com.walletline.database")
        }
    }
}

android {
    namespace = "com.walletline"
    compileSdk = AndroidApplication.CompileSdkVersion
    defaultConfig {
        minSdk = AndroidApplication.MinSdkVersion
        targetSdk = AndroidApplication.TargetSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

tasks.named<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask>("dependencyUpdates")
    .configure {
        checkForGradleUpdate = true
        outputFormatter = "json"
        outputDir = "build/dependencyUpdates"
        reportfileName = "report"
    }

dependencies {
    configurations
        .filter { it.name.startsWith("ksp") && it.name.contains("Test") }
        .forEach {
            add(it.name, Mockative.processor)
        }
}