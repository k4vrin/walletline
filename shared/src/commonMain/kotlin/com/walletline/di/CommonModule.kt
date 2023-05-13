package com.walletline.di

import app.cash.sqldelight.db.SqlDriver
import com.russhwolf.settings.coroutines.SuspendSettings
import com.walletline.data.local.device.Device
import com.walletline.data.local.settings.AppSettings
import com.walletline.data.local.settings.MPAppSettings
import com.walletline.data.remote.AuthService
import com.walletline.data.remote.KtorAuthService
import com.walletline.data.repository.AuthRepositoryImpl
import com.walletline.data.repository.DeviceRepositoryImpl
import com.walletline.database.WalletlineDB
import com.walletline.di.util.CoroutineDispatchers
import com.walletline.domain.repository.AuthRepository
import com.walletline.domain.repository.DeviceRepository
import com.walletline.domain.use_case.DummyUseCase
import com.walletline.domain.use_case.auth.AuthUseCase
import com.walletline.domain.use_case.auth.Register
import com.walletline.domain.use_case.auth.ResendOtp
import com.walletline.domain.use_case.auth.VerifyOtp
import com.walletline.domain.use_case.common.CommonUseCase
import com.walletline.domain.use_case.common.CountDownTimer
import com.walletline.domain.use_case.validator.ValidateEmail
import com.walletline.domain.use_case.validator.ValidateUseCase
import com.walletline.domain.util.PatternChecker
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val commonModule = module {

    single { provideSqlDatabase(sqlDriver = get()) }
    single { provideHttpClient(engine = get()) }
    single { provideAppSetting(settings = get()) }

    single { provideAuthService(client = get()) }
    single { provideAuthRepo(authService = get(), appSettings = get()) }
    single { provideDeviceRepo(device = get()) }


    factory { provideAuthUseCase(authRepository = get(), deviceRepository = get(), dispatchers = get()) }
    factory { provideValidateUseCase(patternChecker = get()) }
    factory { provideCommonUseCase() }
    factory { provideDummyUseCase(appSettings = get(), dispatchers = get()) }

}

fun provideCommonUseCase() = CommonUseCase(
    countDownTimer = CountDownTimer()
)

private fun provideDummyUseCase(appSettings: AppSettings, dispatchers: CoroutineDispatchers): DummyUseCase = DummyUseCase(appSettings, dispatchers)

private fun provideAuthService(client: HttpClient): AuthService = KtorAuthService(client = client)

private fun provideAuthRepo(authService: AuthService, appSettings: AppSettings): AuthRepository = AuthRepositoryImpl(authService, appSettings)

private fun provideDeviceRepo(device: Device): DeviceRepository = DeviceRepositoryImpl(device)

private fun provideAuthUseCase(authRepository: AuthRepository, deviceRepository: DeviceRepository, dispatchers: CoroutineDispatchers): AuthUseCase = AuthUseCase(
    register = Register(dispatchers, authRepository, deviceRepository),
    verifyOtp = VerifyOtp(authRepository, dispatchers),
    resendOtp = ResendOtp(authRepository, dispatchers)
)

private fun provideValidateUseCase(patternChecker: PatternChecker): ValidateUseCase = ValidateUseCase(validateEmail = ValidateEmail(patternChecker))

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
//        url {
//            protocol = URLProtocol.HTTPS
//            host = HttpRoutes.Host
//        }
    }

    /**
     * Throws error for non-2xx responses
     *
     * **See Also** [Response validation](https://ktor.io/docs/response-validation.html)
     */
    expectSuccess = true
}