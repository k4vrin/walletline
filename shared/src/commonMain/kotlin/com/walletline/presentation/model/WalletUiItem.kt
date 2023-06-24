package com.walletline.presentation.model

import com.walletline.data.util.randomUUID

data class WalletUiItem(
    val id: String = randomUUID(),
    val title: String,
    val balance: String,
    val description: String?,
    val lines: List<WalletLineUiItem>
)
