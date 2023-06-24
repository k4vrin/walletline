package com.walletline.domain.use_case.wallet

data class WalletUseCase(
    val createWallet: CreateWallet,
    val createLine: CreateLine,
    val getWallets: GetWallets,
    val getWallet: GetWallet,
    val editWallet: EditWallet,
    val deleteWallet: DeleteWallet
)
