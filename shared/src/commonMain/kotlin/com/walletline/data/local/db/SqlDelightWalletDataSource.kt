package com.walletline.data.local.db

import app.cash.sqldelight.coroutines.asFlow
import com.walletline.database.WalletlineDB
import database.WalletEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SqlDelightWalletDataSource(
    db: WalletlineDB,
) : WalletDataSource {

    private val queries = db.walletQueries

    override suspend fun insertWallet(wallet: WalletEntity) {
        with(wallet) {
            queries.insertWallet(
                id = id,
                name = name,
                balance = balance,
                description = description
            )
        }
    }

    override fun getAllWallets(): Flow<List<WalletEntity>> {
        return queries.getAllWallets().asFlow().map { it.executeAsList() }
    }

    override suspend fun getWalletById(id: String): WalletEntity? {
        return queries.getWalletById(id = id).executeAsOneOrNull()
    }

    override suspend fun deleteWalletById(id: String) {
        queries.deleteWalletById(id = id)
    }

    override suspend fun deleteAllWallets() {
        queries.deleteAllWallets()
    }
}