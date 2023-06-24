package com.walletline.domain.use_case.wallet

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.walletline.di.util.CoroutineDispatchers
import com.walletline.domain.model.wallet.Wallet
import com.walletline.domain.repository.WalletRepository
import com.walletline.domain.util.Resource
import kotlinx.coroutines.withContext

class GetWallet(
    private val walletRepository: WalletRepository,
    private val dispatchers: CoroutineDispatchers,
) {
    @NativeCoroutines
    suspend fun execute(walletId: String): Resource<Wallet?, Boolean> {
        return withContext(dispatchers.database) {
            kotlin.runCatching {
                walletRepository.getWalletById(walletId)
            }
                .fold(
                    onSuccess = { wallet ->
                        Resource.Success(wallet)
                    },
                    onFailure = {
                        Resource.Error(data = false, message = it.message)
                    }
                )
        }
    }
}