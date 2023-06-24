package com.walletline.util

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import app.cash.sqldelight.driver.native.wrapConnection
import co.touchlab.sqliter.DatabaseConfiguration
import com.walletline.database.WalletlineDB

actual fun testDBConnection(): SqlDriver {
    val schema = WalletlineDB.Schema
    return NativeSqliteDriver(
        configuration = DatabaseConfiguration(
            name = "WalletlineDB",
            version = schema.version,
            create = { connection ->
                wrapConnection(connection) {schema.create(it)}
            },
            upgrade = { connection, oldVersion, newVersion ->
                wrapConnection(connection) { schema.migrate(it, oldVersion, newVersion) }
            },
            inMemory = true
        )
    )
}