object MultiplatformSettings {
    const val Version = "1.0.0"
    val mps by lazy { "com.russhwolf:multiplatform-settings:$Version" }
    val mpsTest by lazy { "com.russhwolf:multiplatform-settings-test:$Version" }
    val mpsCoroutine by lazy { "com.russhwolf:multiplatform-settings-coroutines:$Version" }
    val mpsDataStore by lazy { "com.russhwolf:multiplatform-settings-datastore:$Version" }

    const val OptInSettings = "com.russhwolf.settings.ExperimentalSettingsApi"
    const val OptInSettingsImpl = "com.russhwolf.settings.ExperimentalSettingsImplementation"
}