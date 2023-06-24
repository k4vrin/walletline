package com.walletline.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.sqlite.db.SupportSQLiteDatabase
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
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { provideDispatchers() }
    single { provideHttpClientEngine() }
    single { provideDataStore(context = get(), dispatchers = get()) }
    single { provideSqlDriver(context = get()) }
    single { provideMpSettings(dataStore = get()) }
    single { provideDevice() }
    single { providePatternChecker() }
}

private fun provideSqlDriver(
    context: Context,
): SqlDriver = AndroidSqliteDriver(
    schema = WalletlineDB.Schema,
    context = context,
    name = "walletline.db",
    callback = object : AndroidSqliteDriver.Callback(WalletlineDB.Schema) {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            db.setForeignKeyConstraintsEnabled(true)
        }
    }
)

private fun provideHttpClientEngine(): HttpClientEngine = OkHttp.create()

private fun provideMpSettings(dataStore: DataStore<Preferences>): SuspendSettings =
    DataStoreSettings(dataStore)

private fun provideDispatchers(): CoroutineDispatchers = CoroutineDispatchers(
    database = Dispatchers.IO,
    network = Dispatchers.IO,
    disk = Dispatchers.Default,
    ui = Dispatchers.Main
)

private fun provideDataStore(context: Context, dispatchers: CoroutineDispatchers): DataStore<Preferences> = PreferenceDataStoreFactory.create(
    scope = CoroutineScope(dispatchers.database + SupervisorJob()),
    produceFile = {context.applicationContext.preferencesDataStoreFile("WalletlineDS")}
)

private fun provideDevice(): Device = DeviceImpl()

private fun providePatternChecker(): EmailPatternChecker = EmailPatternCheckerImpl()