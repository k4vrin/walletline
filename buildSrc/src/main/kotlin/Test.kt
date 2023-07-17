object Test {
    private const val JUnitVersion = "4.13.2"
    private const val robolectricVersion = "4.8.2"
    private const val turbineVersion = "1.0.0"
    val junit by lazy { "junit:junit:$JUnitVersion" }
    val robolectric by lazy { "org.robolectric:robolectric:$robolectricVersion" }
    val turbine by lazy { "app.cash.turbine:turbine:$turbineVersion" }
}


