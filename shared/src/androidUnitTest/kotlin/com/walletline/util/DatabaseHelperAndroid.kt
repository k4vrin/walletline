package com.walletline.util

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.walletline.database.WalletlineDB

actual fun testDBConnection(): SqlDriver {
    // Try to use the android driver (which only works if we're on robolectric).
    // Fall back to jdbc if that fails.
    return try {
        val app = ApplicationProvider.getApplicationContext<Application>()
        AndroidSqliteDriver(WalletlineDB.Schema, app, "WalletlineDB")
    } catch (exception: IllegalStateException) {
        JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
            .also { WalletlineDB.Schema.create(it) }
    }
}