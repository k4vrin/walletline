package com.walletline.di

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.walletline.database.WalletLineDB
import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { provideHttpClientEngine() }
    single { provideSqlDriver() }
}

private fun provideSqlDriver(): SqlDriver = NativeSqliteDriver(
    schema = WalletLineDB.Schema,
    name = "walletline.db"
)

/**
 * Darwin does not support connection time out
 * **See** [Timeout](https://ktor.io/docs/timeout.html#limitations)
 */
private fun provideHttpClientEngine(): HttpClientEngine = Darwin.create()