package com.walletline.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.coroutines.SuspendSettings
import com.russhwolf.settings.coroutines.toSuspendSettings
import com.walletline.database.WalletlineDB
import com.walletline.di.util.CoroutineDispatchers
import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

actual fun platformModule(): Module = module {
    single { provideDispatchers() }
    single { provideHttpClientEngine() }
    single { provideSqlDriver() }
    single { provideMpSettings(dispatchers = get()) }
}

private fun provideDispatchers(): CoroutineDispatchers = CoroutineDispatchers(
    database = Dispatchers.Default,
    network = Dispatchers.Default,
    disk = Dispatchers.Default,
    ui = Dispatchers.Main
)

private fun provideSqlDriver(): SqlDriver = NativeSqliteDriver(
    schema = WalletlineDB.Schema,
    name = "walletline.db"
)

/**
 * Darwin does not support connection time out.
 * **See** [Timeout](https://ktor.io/docs/timeout.html#limitations)
 */
private fun provideHttpClientEngine(): HttpClientEngine = Darwin.create()

private fun provideMpSettings(dispatchers: CoroutineDispatchers): SuspendSettings {
    val userDefaults: NSUserDefaults = NSUserDefaults.standardUserDefaults
    return NSUserDefaultsSettings(userDefaults).toSuspendSettings(dispatcher = dispatchers.database)
}

