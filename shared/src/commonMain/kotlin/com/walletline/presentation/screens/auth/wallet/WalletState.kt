package com.walletline.presentation.screens.wallet

import com.walletline.domain.model.LineData
import com.walletline.domain.model.PartnerData
import com.walletline.domain.model.wallet.Wallet
import com.walletline.domain.model.wallet.WalletLine

data class WalletState(
    val walletsList: List<Wallet> = listOf(),
    val currentWallet: Wallet,
    val walletId: String? = "0",
    val showEmptyWallet: Boolean = false,
    val partnersList: List<PartnerData> = listOf(),
    val linesList: List<LineData> = listOf(),
    val accountTitle: String = "Account title",
    val accountBalanceValue: String = "23456.45",
    val accountBalanceCorrectValue: String = "23456",
    val accountBalanceDecimalValue: String = "45",
    val balanceValueCurrency: String = "$",
    val currentLines: String = "",
) {
    constructor() : this(
        walletsList = listOf(),
        currentWallet = Wallet(
            id ="1",
            name = "A",
            balance = "23456.45",
            description = "sds",
            lines = listOf(WalletLine(name = "A", percentage = 50, balance = "23456.45", description = "dsd", categories = listOf()))
        ),
        walletId = "0",
        showEmptyWallet = false,
        partnersList = listOf(),
        linesList = listOf(),
        accountTitle = "Account title",
        accountBalanceValue = "23456.45",
        accountBalanceCorrectValue = "23456",
        accountBalanceDecimalValue = "45",
        balanceValueCurrency = "$",
        currentLines = "20|30|50",
    )
}