/**
 * Jetpack Compose
 *
 * **See Also** [Quick start](https://developer.android.com/jetpack/compose/setup)
 *
 * **See Also** [BOM to library](https://developer.android.com/jetpack/compose/bom/bom-mapping)
 */
object Compose {
    const val composeBOM = "androidx.compose:compose-bom:2023.06.01"
//    val material by lazy { "androidx.compose.material:material" }
    val material3 by lazy { "androidx.compose.material3:material3" }
    val toolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview" }
    val tooling by lazy { "androidx.compose.ui:ui-tooling" }
    val junit by lazy { "androidx.compose.ui:ui-test-junit4" }
    val testManifest by lazy { "androidx.compose.ui:ui-test-manifest" }
    val iconExtended by lazy { "androidx.compose.material:material-icons-extended" }
    // Do not use without material3 !!!
    val windowSizeClass by lazy { "androidx.compose.material3:material3-window-size-class" }

    private const val ActivityVersion = "1.7.2"
    val activity by lazy { "androidx.activity:activity-compose:$ActivityVersion" }

    const val CompilerVersion = "1.4.8"
    val compiler by lazy { "androidx.compose.compiler:compiler:${CompilerVersion}" }

    private const val NavigationVersion = "2.6.0"
    val navigation by lazy { "androidx.navigation:navigation-compose:$NavigationVersion" }

    private const val ViewModelVersion = "2.6.1"
    val viewModels by lazy { "androidx.lifecycle:lifecycle-viewmodel-compose:$ViewModelVersion" }

    private const val RuntimeVersion = "1.4.3"
    val runtime by lazy { "androidx.compose.runtime:runtime:$RuntimeVersion" }
}