package com.walletline.domain.model.wallet

import com.walletline.data.util.randomUUID

data class Wallet(
    /**
     * **Don't assign id manually**
     */
    val id: String = randomUUID(),
    val name: String,
    val balance: String,
    val description: String?,
    val lines: List<WalletLine>,
)
