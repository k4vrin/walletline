package com.walletline.data.repository

import com.walletline.data.local.db.WalletDataSource
import com.walletline.data.local.db.WalletLineDataSource
import com.walletline.data.mapper.toWallet
import com.walletline.data.mapper.toWalletEntity
import com.walletline.data.mapper.toWalletLine
import com.walletline.domain.model.wallet.Wallet
import com.walletline.domain.model.wallet.WalletLine
import com.walletline.domain.repository.WalletRepository
import database.WalletLineEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class WalletRepositoryImpl(
    private val walletDataSource: WalletDataSource,
    private val lineDataSource: WalletLineDataSource,
) : WalletRepository {

    override suspend fun insertWallet(wallet: Wallet) {
        walletDataSource.insertWallet(wallet.toWalletEntity())

        wallet.lines.forEach { line ->
            lineDataSource.insertLine(
                WalletLineEntity(
                    id = line.id,
                    wallet_id = line.walletId,
                    name = line.name,
                    percentage = line.percentage,
                    balance = line.balance,
                    description = line.description,
                    categories = line.categories
                )
            )
        }

    }

    override suspend fun editWallet(wallet: Wallet) {
        walletDataSource.insertWallet(wallet.toWalletEntity())
    }

    override suspend fun insertLine(line: WalletLine) {
        lineDataSource.insertLine(
            WalletLineEntity(
                id = line.id,
                wallet_id = line.walletId,
                name = line.name,
                percentage = line.percentage,
                balance = line.balance,
                description = line.description,
                categories = line.categories
            )
        )
    }

    override fun getAllWallets(): Flow<List<Wallet>> {
        return walletDataSource.getAllWallets().map { walletEntities ->
            walletEntities.map { walletEntity ->
                val lines = lineDataSource.getAllLines(walletEntity.id).first()
                    .map { line -> line.toWalletLine() }
                walletEntity.toWallet(lines = lines)
            }
        }
    }

    override suspend fun getWalletById(id: String): Wallet? {
        val lines = lineDataSource.getAllLines(id).first().map { it.toWalletLine() }
        return walletDataSource.getWalletById(id)?.toWallet(lines = lines)
    }

    override suspend fun getLineById(lineId: String): WalletLine? {
        return lineDataSource.getLineById(lineId)?.toWalletLine()
    }

    override fun getAllLines(walletId: String): Flow<List<WalletLine>> {
        return lineDataSource.getAllLines(walletId).map { lines -> lines.map { line -> line.toWalletLine() } }
    }

    override suspend fun deleteWalletById(id: String) {
        walletDataSource.deleteWalletById(id)
    }

    override suspend fun deleteLineById(lineId: String) {
        lineDataSource.deleteLineById(lineId)
    }

    override suspend fun deleteAllWallets() {
        walletDataSource.deleteAllWallets()
    }

    override suspend fun deleteAllLines(walletId: String) {
        lineDataSource.deleteAllLines(walletId)
    }


}