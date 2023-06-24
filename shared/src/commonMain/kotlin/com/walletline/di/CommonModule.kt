package com.walletline.di

import app.cash.sqldelight.db.SqlDriver
import com.russhwolf.settings.coroutines.SuspendSettings
import com.walletline.data.local.db.SqlDelightWalletDataSource
import com.walletline.data.local.db.SqlDelightWalletLineDataSource
import com.walletline.data.local.db.WalletDataSource
import com.walletline.data.local.db.WalletLineDataSource
import com.walletline.data.local.device.Device
import com.walletline.data.local.settings.AppSettings
import com.walletline.data.local.settings.MPAppSettings
import com.walletline.data.remote.firebase.auth.FirebaseAuthClient
import com.walletline.data.remote.firebase.auth.FirebaseAuthClientImpl
import com.walletline.data.remote.server.AuthService
import com.walletline.data.remote.server.KtorAuthService
import com.walletline.data.repository.AuthRepositoryImpl
import com.walletline.data.repository.DeviceRepositoryImpl
import com.walletline.data.repository.WalletRepositoryImpl
import com.walletline.database.WalletlineDB
import com.walletline.di.util.CoroutineDispatchers
import com.walletline.di.util.listOfStringsAdapter
import com.walletline.domain.repository.AuthRepository
import com.walletline.domain.repository.DeviceRepository
import com.walletline.domain.repository.WalletRepository
import com.walletline.domain.use_case.auth.AuthUseCase
import com.walletline.domain.use_case.auth.GetAdmission
import com.walletline.domain.use_case.auth.GetPattern
import com.walletline.domain.use_case.auth.Register
import com.walletline.domain.use_case.auth.ResendOtp
import com.walletline.domain.use_case.auth.SignInWithSocial
import com.walletline.domain.use_case.auth.SetAdmission
import com.walletline.domain.use_case.auth.SetOnBoarded
import com.walletline.domain.use_case.auth.VerifyOtp
import com.walletline.domain.use_case.common.CommonUseCase
import com.walletline.domain.use_case.common.CountDownTimer
import com.walletline.domain.use_case.validator.ValidateEmail
import com.walletline.domain.use_case.validator.ValidateUseCase
import com.walletline.domain.use_case.wallet.CreateLine
import com.walletline.domain.use_case.wallet.CreateWallet
import com.walletline.domain.use_case.wallet.DeleteWallet
import com.walletline.domain.use_case.wallet.EditWallet
import com.walletline.domain.use_case.wallet.GetWallet
import com.walletline.domain.use_case.wallet.GetWallets
import com.walletline.domain.use_case.wallet.WalletUseCase
import com.walletline.domain.util.EmailPatternChecker
import database.WalletLineEntity
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
    single { provideFirebaseAuthClient() }

    single { provideAuthService(client = get()) }
    single { provideAuthRepo(authService = get(), appSettings = get(), firebaseAuthClient = get()) }
    single { provideDeviceRepo(device = get()) }

    single { provideWalletDataSource(database = get()) }
    single { provideWalletLineDataSource(database = get()) }
    single { provideWalletRepository(walletDataSource = get(), lineDataSource = get()) }

    factory { provideAuthUseCase(authRepository = get(), deviceRepository = get(), dispatchers = get()) }
    factory { provideValidateUseCase(emailPatternChecker = get()) }
    factory {
        provideAuthUseCase(
            authRepository = get(),
            deviceRepository = get(),
            dispatchers = get()
        )
    }
    factory { provideCommonUseCase() }
    factory { provideWalletUseCase(walletRepository = get(), dispatchers = get()) }

}

fun provideWalletRepository(
    walletDataSource: WalletDataSource,
    lineDataSource: WalletLineDataSource
): WalletRepository = WalletRepositoryImpl(walletDataSource, lineDataSource)

fun provideWalletLineDataSource(database: WalletlineDB): WalletLineDataSource {
    return SqlDelightWalletLineDataSource(db = database)
}

fun provideWalletDataSource(database: WalletlineDB): WalletDataSource {
    return SqlDelightWalletDataSource(db = database)
}

fun provideCommonUseCase() = CommonUseCase(
    countDownTimer = CountDownTimer()
)

fun provideWalletUseCase(walletRepository: WalletRepository, dispatchers: CoroutineDispatchers) = WalletUseCase(
    createWallet = CreateWallet(walletRepository = walletRepository, dispatchers = dispatchers),
    createLine = CreateLine(walletRepository = walletRepository, dispatchers = dispatchers),
    getWallets = GetWallets(walletRepository = walletRepository, dispatchers = dispatchers),
    getWallet = GetWallet(walletRepository = walletRepository, dispatchers = dispatchers),
    editWallet = EditWallet(walletRepository = walletRepository, dispatchers = dispatchers),
    deleteWallet = DeleteWallet(walletRepository = walletRepository, dispatchers = dispatchers),
)

private fun provideAuthService(client: HttpClient): AuthService = KtorAuthService(client = client)
private fun provideFirebaseAuthClient(): FirebaseAuthClient = FirebaseAuthClientImpl()

private fun provideAuthRepo(
    authService: AuthService,
    appSettings: AppSettings,
    firebaseAuthClient: FirebaseAuthClient,
): AuthRepository = AuthRepositoryImpl(authService, appSettings, firebaseAuthClient)

private fun provideDeviceRepo(device: Device): DeviceRepository = DeviceRepositoryImpl(device)

private fun provideAuthUseCase(
    authRepository: AuthRepository,
    deviceRepository: DeviceRepository,
    dispatchers: CoroutineDispatchers,
): AuthUseCase = AuthUseCase(
    register = Register(dispatchers, authRepository, deviceRepository),
    verifyOtp = VerifyOtp(authRepository, dispatchers),
    resendOtp = ResendOtp(authRepository, dispatchers),
    setAdmission = SetAdmission(authRepository, dispatchers),
    getAdmission = GetAdmission(authRepository, dispatchers),
    getPattern = GetPattern(authRepository),
    setOnBoarded = SetOnBoarded(authRepository),
    signInWithSocial = SignInWithSocial(authRepository, dispatchers)
)

private fun provideValidateUseCase(emailPatternChecker: EmailPatternChecker): ValidateUseCase = ValidateUseCase(validateEmail = ValidateEmail(emailPatternChecker))


private fun provideAppSetting(settings: SuspendSettings): AppSettings =
    MPAppSettings(settings = settings)

private fun provideSqlDatabase(
    sqlDriver: SqlDriver,
): WalletlineDB = WalletlineDB(
    driver = sqlDriver,
    walletLineEntityAdapter = WalletLineEntity.Adapter(categoriesAdapter = listOfStringsAdapter)
)

private fun provideHttpClient(
    engine: HttpClientEngine,
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
        // host has a bug on Darwin client
//        url {
//            protocol = URLProtocol.HTTPS
//            host = HttpRoutes.Host
//        }
    }

    /**
     * Throws error for non-2xx responses
     *
     * **See Also** [SafeRequest](com.walletline.data.util.safeRequest)
     * **See Also** [Response validation](https://ktor.io/docs/response-validation.html)
     */
    expectSuccess = true
}