object Koin {
    private const val CoreVersion = "3.4.0"
    private const val AndroidVersion = "3.4.0"
    private const val ComposeVersion = "3.4.3"

    val core by lazy { "io.insert-koin:koin-core:${CoreVersion}" }
    val android by lazy { "io.insert-koin:koin-android:${AndroidVersion}" }
    val compose by lazy { "io.insert-koin:koin-androidx-compose:${ComposeVersion}" }
}