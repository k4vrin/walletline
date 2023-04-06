object SqlDelight {
    const val Version = "2.0.0-alpha05"
    const val Plugin = "app.cash.sqldelight"
    val runtime by lazy { "app.cash.sqldelight:runtime:${Version}" }
    val androidDriver by lazy { "app.cash.sqldelight:android-driver:${Version}" }
    val nativeDriver by lazy { "app.cash.sqldelight:native-driver:${Version}" }
    val coroutineExt by lazy { "app.cash.sqldelight:coroutines-extensions:${Version}" }
}