package com.walletline.data.mapper

import com.walletline.domain.model.wallet.Wallet
import com.walletline.domain.model.wallet.WalletLine
import database.WalletEntity
import database.WalletLineEntity

fun Wallet.toWalletEntity() = WalletEntity(
    id = id,
    name = name,
    balance = balance,
    description = description
)

fun WalletEntity.toWallet(lines: List<WalletLine> = emptyList()) = Wallet(
    id = id,
    name = name,
    balance = balance,
    description = description,
    lines = lines
)

fun WalletLine.toWalletLineEntity() = WalletLineEntity(
    id = id,
    wallet_id = walletId,
    name = name,
    percentage = percentage,
    balance = balance,
    description = description,
    categories = categories
)

fun WalletLineEntity.toWalletLine() = WalletLine(
    id = id,
    walletId = wallet_id,
    name = name,
    percentage = percentage,
    balance = balance,
    description = description,
    categories = categories
)