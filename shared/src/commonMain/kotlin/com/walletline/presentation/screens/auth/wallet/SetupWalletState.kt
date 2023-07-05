package com.walletline.presentation.screens.wallet

import com.walletline.domain.model.LineData
import com.walletline.domain.model.PartnerData
import com.walletline.domain.model.wallet.Wallet
import com.walletline.domain.model.wallet.WalletLine

data class SetupWalletState(
    val walletTitle: String,
    val walletBalance: String,
    val walletDescription: String,
    val walletLines: List<WalletLine>,
) {
    constructor() : this(
        walletTitle = "",
        walletBalance = "",
        walletDescription = "",
        walletLines = listOf()
    )
}