package com.walletline.domain.use_case.wallet.create_line

import com.walletline.data.util.randomUUID
import com.walletline.domain.model.wallet.Wallet
import com.walletline.domain.model.wallet.WalletLine
import com.walletline.domain.repository.WalletRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CreateLineTestRepo1 : WalletRepository {

    override suspend fun insertWallet(wallet: Wallet) {

    }

    override suspend fun editWallet(wallet: Wallet) {
        TODO("Not yet implemented")
    }

    override suspend fun insertLine(line: WalletLine) {

    }

    override fun getAllWallets(): Flow<List<Wallet>> {
        val wallets = buildList {
            repeat(10) { index ->
                add(
                    Wallet(
                        name = "wallet $index",
                        balance = "12345.55",
                        description = null,
                        lines = emptyList()
                    )
                )
            }
        }
        return flowOf(wallets)
    }

    override suspend fun getWalletById(id: String): Wallet? {
        return Wallet(
            name = "wallet",
            balance = "12345.55",
            description = null,
            lines = buildList {
                repeat(4) { ind ->
                    add(
                        WalletLine(
                            walletId = randomUUID(),
                            name = "$ind",
                            balance = "4444",
                            percentage = 5,
                            description = null,
                            categories = emptyList()
                        )
                    )
                }
            }
        )
    }

    override suspend fun getLineById(lineId: String): WalletLine? {
        TODO("Not yet implemented")
    }

    override fun getAllLines(walletId: String): Flow<List<WalletLine>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteWalletById(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteLineById(lineId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllWallets() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllLines(walletId: String) {
        TODO("Not yet implemented")
    }
}