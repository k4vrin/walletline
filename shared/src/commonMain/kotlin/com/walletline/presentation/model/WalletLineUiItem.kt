package com.walletline.presentation.model

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.walletline.data.util.randomUUID

data class WalletLineUiItem(
    val id: String = randomUUID(),
    val walletId: String,
    val name: String,
    val percentage: Long,
    val balance: BigDecimal,
    val description: String?,
    val categories: List<String>,
)
