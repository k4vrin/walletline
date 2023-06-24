package com.walletline.domain.repository

import com.walletline.domain.model.wallet.Wallet
import com.walletline.domain.model.wallet.WalletLine
import kotlinx.coroutines.flow.Flow

interface WalletRepository {
    suspend fun insertWallet(wallet: Wallet)
    suspend fun editWallet(wallet: Wallet)
    suspend fun insertLine(line: WalletLine)
    fun getAllWallets(): Flow<List<Wallet>>
    suspend fun getWalletById(id: String): Wallet?
    suspend fun getLineById(lineId: String): WalletLine?
    fun getAllLines(walletId: String): Flow<List<WalletLine>>
    suspend fun deleteWalletById(id: String)
    suspend fun deleteLineById(lineId: String)
    suspend fun deleteAllWallets()
    suspend fun deleteAllLines(walletId: String)
}