object Firebase {

    const val PluginVersion = "4.3.15"
    const val Plugin = "com.google.gms.google-services"

    private const val Version = "32.2.0"
    val bom by lazy { "com.google.firebase:firebase-bom:$Version" }
    val auth by lazy { "com.google.firebase:firebase-auth-ktx" }
    val kmmAuth by lazy { "dev.gitlive:firebase-auth:1.8.1" }

    val googlePlayServiceAuth by lazy { "com.google.android.gms:play-services-auth:20.6.0" }
}