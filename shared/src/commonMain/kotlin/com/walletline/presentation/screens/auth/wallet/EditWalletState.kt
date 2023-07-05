package com.walletline.presentation.screens.wallet

import com.walletline.domain.model.wallet.Wallet
import com.walletline.domain.model.wallet.WalletLine

data class EditWalletState(
    val wallet: Wallet?,
    val walletId:String = "0",
    val walletTitle:String = "salary",
    val walletBalance: String ="0.0",
    val walletDescription: String ="",
    val walletLines: List<WalletLine> = listOf(),
    val editWalletMode:Boolean = true
    ) {
    constructor() : this(
        wallet = null,
        walletId = "0",
        walletTitle = "false",
        walletBalance = "0.0",
        walletDescription = "",
        walletLines = listOf(),
        editWalletMode = true
    )
}