buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven  { url = uri("https://jitpack.io") }

    }
    dependencies {
        classpath(Build.kotlinGradlePlugin)
        classpath(Build.androidGradlePlugin)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
