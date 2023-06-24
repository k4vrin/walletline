package com.walletline.domain.use_case.wallet

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.walletline.di.util.CoroutineDispatchers
import com.walletline.domain.repository.WalletRepository
import com.walletline.domain.util.Resource
import kotlinx.coroutines.withContext

class DeleteWallet(
    private val walletRepository: WalletRepository,
    private val dispatchers: CoroutineDispatchers
) {
    @NativeCoroutines
    suspend fun execute(walletId: String): Resource<Boolean, Boolean> {
        return withContext(dispatchers.database) {
            kotlin.runCatching {
                walletRepository.deleteWalletById(walletId)
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