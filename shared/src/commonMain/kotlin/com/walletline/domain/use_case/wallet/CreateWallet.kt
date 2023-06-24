package com.walletline.domain.use_case.wallet

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.walletline.data.util.randomUUID
import com.walletline.di.util.CoroutineDispatchers
import com.walletline.domain.model.wallet.CreateWalletError
import com.walletline.domain.model.wallet.Wallet
import com.walletline.domain.model.wallet.WalletLine
import com.walletline.domain.repository.WalletRepository
import com.walletline.domain.util.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class CreateWallet(
    private val walletRepository: WalletRepository,
    private val dispatchers: CoroutineDispatchers,
) {

    @NativeCoroutines
    suspend fun execute(
        name: String,
        balance: String,
        description: String?,
        lines: List<WalletLine>,
    ): Resource<Boolean, CreateWalletError> = coroutineScope {

        val nameAlreadyExist = async(dispatchers.database) {
            walletRepository.getAllWallets().first().map { it.name.lowercase() == name.lowercase() }.any { it }
        }
        if (nameAlreadyExist.await()) return@coroutineScope Resource.Error(CreateWalletError.NameAlreadyExist)

        kotlin.runCatching {
            withContext(dispatchers.database) {
                val walletId = randomUUID()
                walletRepository.insertWallet(
                    Wallet(
                        id = walletId,
                        name = name,
                        balance = balance,
                        description = description,
                        lines = lines.map { it.copy(walletId = walletId) }
                    )
                )
            }
        }.fold(
            onFailure = {
                Resource.Error(CreateWalletError.ErrorHappened)
            },
            onSuccess = {
                Resource.Success(true)
            }
        )

    }
}