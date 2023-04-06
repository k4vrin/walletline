package com.walletline.di

import app.cash.sqldelight.db.SqlDriver
import com.russhwolf.settings.coroutines.SuspendSettings
import com.walletline.data.local.settings.AppSettings
import com.walletline.data.local.settings.MPAppSettings
import com.walletline.database.WalletlineDB
import com.walletline.di.util.CoroutineDispatchers
import com.walletline.domain.use_case.DummyUseCase
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val commonModule = module {

    single { provideSqlDatabase(sqlDriver = get()) }
    single { provideHttpClient(engine = get()) }
    single { provideAppSetting(settings = get()) }

    factory { provideDummyUseCase(appSettings = get(), dispatchers = get()) }

}

private fun provideDummyUseCase(appSettings: AppSettings, dispatchers: CoroutineDispatchers): DummyUseCase = DummyUseCase(appSettings, dispatchers)

private fun provideAppSetting(settings: SuspendSettings): AppSettings = MPAppSettings(settings = settings)

private fun provideSqlDatabase(
    sqlDriver: SqlDriver
): WalletlineDB = WalletlineDB(driver = sqlDriver)

private fun provideHttpClient(
    engine: HttpClientEngine
): HttpClient = HttpClient(engine = engine) {

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
                ignoreUnknownKeys = true
            },
            contentType = ContentType.Application.Json
        )
    }

    install(HttpTimeout) {
        requestTimeoutMillis = 20000
        // Darwin does not support connection time out
        connectTimeoutMillis = 20000
        socketTimeoutMillis = 20000
    }

    defaultRequest {
        // Add base url and default headers in here
    }
}