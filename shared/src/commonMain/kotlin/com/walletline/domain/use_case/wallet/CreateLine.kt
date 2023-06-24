package com.walletline.domain.use_case.wallet

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.walletline.di.util.CoroutineDispatchers
import com.walletline.domain.model.wallet.CreateLineError
import com.walletline.domain.model.wallet.WalletLine
import com.walletline.domain.repository.WalletRepository
import com.walletline.domain.util.Resource
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class CreateLine(
    private val walletRepository: WalletRepository,
    private val dispatchers: CoroutineDispatchers
) {

    @NativeCoroutines
    suspend fun execute(line: WalletLine): Resource<Boolean, CreateLineError> = coroutineScope {
        val wallet = withContext(dispatchers.database) { walletRepository.getWalletById(line.walletId) }

        when {
            wallet == null ->  Resource.Error(data = CreateLineError.NoWallet)
            wallet.lines.sumOf { it.percentage } + line.percentage > 100 -> Resource.Error(data = CreateLineError.NotEnoughBalance)
            else -> {
                val balance = BigDecimal.parseString(wallet.balance) * (line.percentage/100)
                val mLine = WalletLine(
                    id = line.id,
                    walletId = line.walletId,
                    name = line.name,
                    percentage = line.percentage,
                    balance = balance.toStringExpanded(),
                    description = line.description,
                    categories = line.categories

                )
                kotlin.runCatching {
                    withContext(dispatchers.database) {
                        walletRepository.insertLine(mLine)
                    }
                }.fold(
                    onSuccess = {
                        Resource.Success(true)
                    },
                    onFailure = {
                        Resource.Error(CreateLineError.ErrorHappened, message = it.message)
                    }
                )
            }
        }


    }
}