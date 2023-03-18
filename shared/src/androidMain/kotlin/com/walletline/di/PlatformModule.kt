package com.walletline.di

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.walletline.database.WalletLineDB
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { provideSqlDriver(context = get()) }
    single { provideHttpClientEngine() }
}

private fun provideSqlDriver(
    context: Context
): SqlDriver = AndroidSqliteDriver(
    schema = WalletLineDB.Schema,
    context = context,
    name = "walletline.db"
)

private fun provideHttpClientEngine(): HttpClientEngine = OkHttp.create()