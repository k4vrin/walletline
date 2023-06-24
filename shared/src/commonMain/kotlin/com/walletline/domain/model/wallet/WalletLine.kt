package com.walletline.domain.model.wallet

import com.walletline.data.util.randomUUID

data class WalletLine(
    /**
     * **Don't assign id manually**
     */
    val id: String = randomUUID(),
    /**
     * **Don't assign id manually**
     */
    val walletId: String = randomUUID(),
    val name: String,
    val percentage: Long,
    /**
     * **Don't assign manually manually**
     */
    val balance: String = "",
    val description: String?,
    val categories: List<String>,
)
