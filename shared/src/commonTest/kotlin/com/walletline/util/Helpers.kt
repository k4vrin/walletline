package com.walletline.util

import com.walletline.data.remote.server.HttpRoutes
import com.walletline.data.remote.server.KtorAuthService
import com.walletline.di.util.CoroutineDispatchers
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockEngineConfig
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.serialization.json.Json

object Helpers {
    fun mockRequest(
        handler: suspend MockRequestHandleScope.(HttpRequestData) -> HttpResponseData,
    ): KtorAuthService {
        val engine = MockEngine(
            MockEngineConfig().apply {
                requestHandlers.add(handler)
            }
        )
        val client = HttpClient(engine = engine) {

            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        co.touchlab.kermit.Logger.withTag("ktor").v(message)
                    }
                }
            }

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

            install(HttpTimeout) {
                requestTimeoutMillis = 20000
                // Darwin does not support connection time out
                connectTimeoutMillis = 20000
                socketTimeoutMillis = 20000
            }

            defaultRequest {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                url {
                    protocol = URLProtocol.HTTPS
                    host = HttpRoutes.Host
                }
            }

            /**
             * Throws error for non-2xx responses
             *
             * **See Also** [Response validation](https://ktor.io/docs/response-validation.html)
             */
            expectSuccess = true
        }
        return KtorAuthService(client)
    }

    private val testDispatcher = StandardTestDispatcher(TestCoroutineScheduler())
    val testDispatchers = CoroutineDispatchers(
        database = testDispatcher,
        disk = testDispatcher,
        network = testDispatcher,
        ui = testDispatcher
    )
}