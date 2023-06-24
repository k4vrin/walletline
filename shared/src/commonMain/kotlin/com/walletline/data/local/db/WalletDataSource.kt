package com.walletline.data.local.db

import database.WalletEntity
import kotlinx.coroutines.flow.Flow

interface WalletDataSource {
    suspend fun insertWallet(wallet: WalletEntity)
    fun getAllWallets(): Flow<List<WalletEntity>>
    suspend fun getWalletById(id: String): WalletEntity?
    suspend fun deleteWalletById(id: String)
    suspend fun deleteAllWallets()
}