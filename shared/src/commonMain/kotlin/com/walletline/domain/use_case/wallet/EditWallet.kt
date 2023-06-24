package com.walletline.domain.use_case.wallet

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.walletline.di.util.CoroutineDispatchers
import com.walletline.domain.model.wallet.Wallet
import com.walletline.domain.repository.WalletRepository
import com.walletline.domain.util.Resource
import kotlinx.coroutines.withContext

class EditWallet(
    private val walletRepository: WalletRepository,
    private val dispatchers: CoroutineDispatchers,
) {

    @NativeCoroutines
    suspend fun execute(wallet: Wallet): Resource<Boolean, Boolean> {
        return withContext(dispatchers.database) {
            kotlin.runCatching {
                walletRepository.editWallet(wallet)
            }
                .fold(
                    onSuccess = {
                        Resource.Success(true)
                    },
                    onFailure = {
                        Resource.Error(data = false, message = it.message)
                    }
                )
        }
    }
}