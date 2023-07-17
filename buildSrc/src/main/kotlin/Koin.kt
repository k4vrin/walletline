object Koin {
    private const val CoreVersion = "3.4.2"
    private const val TestVersion = "3.4.1"
    private const val AndroidVersion = "3.4.2"
    private const val ComposeVersion = "3.4.5"

    val core by lazy { "io.insert-koin:koin-core:${CoreVersion}" }
    val android by lazy { "io.insert-koin:koin-android:${AndroidVersion}" }
    val compose by lazy { "io.insert-koin:koin-androidx-compose:${ComposeVersion}" }
    val test by lazy { "io.insert-koin:koin-test:$TestVersion" }
}