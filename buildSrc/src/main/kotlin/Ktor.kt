object Ktor {
    private const val Version = "2.3.2"
    val core by lazy {  "io.ktor:ktor-client-core:${Version}"}
    val okhttp by lazy {  "io.ktor:ktor-client-okhttp:${Version}"}
    val ios by lazy {  "io.ktor:ktor-client-ios:${Version}"}
    val contentNegotiation by lazy { "io.ktor:ktor-client-content-negotiation:${Version}" }
    val serialization by lazy { "io.ktor:ktor-serialization-kotlinx-json:${Version}" }
    val logging by lazy { "io.ktor:ktor-client-logging:${Version}" }
    val clientMock by lazy { "io.ktor:ktor-client-mock:${Version}" }
}