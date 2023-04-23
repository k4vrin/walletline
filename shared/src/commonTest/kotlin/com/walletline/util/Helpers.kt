package com.walletline.util

import com.walletline.data.remote.KtorAuthService
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockEngineConfig
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun mockRequest(
    handler: suspend MockRequestHandleScope.(HttpRequestData) -> HttpResponseData,
): KtorAuthService {
    val engine = MockEngine(
        MockEngineConfig().apply {
            requestHandlers.add(handler)
        }
    )
    val client = HttpClient(engine = engine) {
        install(ContentNegotiation) {
            json(
                json = Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                },
                contentType = ContentType.Application.Any
            )
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }

        expectSuccess = true
    }
    return KtorAuthService(client)
}