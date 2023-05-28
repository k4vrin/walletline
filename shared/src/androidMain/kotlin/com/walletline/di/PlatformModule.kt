package com.walletline.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.russhwolf.settings.coroutines.SuspendSettings
import com.russhwolf.settings.datastore.DataStoreSettings
import com.walletline.data.local.device.Device
import com.walletline.data.local.device.DeviceImpl
import com.walletline.database.WalletlineDB
import com.walletline.di.util.CoroutineDispatchers
import com.walletline.domain.util.EmailPatternChecker
import com.walletline.domain.util.EmailPatternCheckerImpl
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { provideDispatchers() }
    single { provideHttpClientEngine() }
    single { provideSqlDriver(context = get()) }
    single { provideMpSettings(context = get()) }
    single { provideDevice() }
    single { providePatternChecker() }
}

private fun provideSqlDriver(
    context: Context
): SqlDriver = AndroidSqliteDriver(
    schema = WalletlineDB.Schema,
    context = context,
    name = "walletline.db"
)
private fun provideHttpClientEngine(): HttpClientEngine = OkHttp.create()

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "WalletlineDS")

private fun provideMpSettings(context: Context): SuspendSettings = DataStoreSettings(context.dataStore)

private fun provideDispatchers(): CoroutineDispatchers = CoroutineDispatchers(
    database = Dispatchers.IO,
    network = Dispatchers.IO,
    disk = Dispatchers.Default,
    ui = Dispatchers.Main
)

private fun provideDevice(): Device = DeviceImpl()

private fun providePatternChecker(): EmailPatternChecker = EmailPatternCheckerImpl()