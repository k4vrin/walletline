package com.walletline.presentation.screens.wallet.wallet_screen

import com.walletline.domain.model.wallet.Wallet

data class WalletState(
    val wallets: List<Wallet> = emptyList(),
    val isLinesTabSelected: Boolean = true
) {
    constructor() : this(
        wallets = emptyList(),
        isLinesTabSelected = true
    )
}
