package com.walletline.data.local.db

import database.WalletLineEntity
import kotlinx.coroutines.flow.Flow

interface WalletLineDataSource {
    suspend fun insertLine(walletLine: WalletLineEntity)
    fun getAllLines(walletId: String): Flow<List<WalletLineEntity>>
    suspend fun getLineById(lineId: String): WalletLineEntity?
    suspend fun deleteLineById(lineId: String)
    suspend fun deleteAllLines(walletId: String)
}