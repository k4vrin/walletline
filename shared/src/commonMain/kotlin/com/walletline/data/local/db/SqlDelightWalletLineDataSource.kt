package com.walletline.data.local.db

import app.cash.sqldelight.coroutines.asFlow
import com.walletline.database.WalletlineDB
import database.WalletLineEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SqlDelightWalletLineDataSource(
    db: WalletlineDB
) : WalletLineDataSource {

    private val queries = db.walletLineQueries

    override suspend fun insertLine(walletLine: WalletLineEntity) {
        with(walletLine) {
            queries.insertWalletLine(
                id = id,
                wallet_id = wallet_id,
                name = name,
                percentage = percentage,
                balance = balance,
                description = description,
                categories = categories
            )
        }
    }

    override fun getAllLines(walletId: String): Flow<List<WalletLineEntity>> {
        return queries.getAllWalletLines(walletId).asFlow().map { it.executeAsList() }
    }

    override suspend fun getLineById(lineId: String): WalletLineEntity? {
        return queries.getWalletLineById(lineId).executeAsOneOrNull()
    }

    override suspend fun deleteLineById(lineId: String) {
        queries.deleteWalletLineById(lineId)
    }

    override suspend fun deleteAllLines(walletId: String) {
        queries.deleteAllWalletLines(walletId)
    }

}