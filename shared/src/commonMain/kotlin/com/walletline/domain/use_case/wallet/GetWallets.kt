package com.walletline.domain.use_case.wallet

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.walletline.di.util.CoroutineDispatchers
import com.walletline.domain.model.wallet.Wallet
import com.walletline.domain.repository.WalletRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetWallets(
    private val walletRepository: WalletRepository,
    private val dispatchers: CoroutineDispatchers
) {
    @NativeCoroutines
    fun execute(): Flow<List<Wallet>> =
        walletRepository.getAllWallets().flowOn(dispatchers.database)

}