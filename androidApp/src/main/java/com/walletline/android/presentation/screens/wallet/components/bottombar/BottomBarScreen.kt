package com.walletline.android.presentation.screens.wallet.components.bottombar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.walletline.android.R
import com.walletline.android.presentation.screens.destinations.DirectionDestination
import com.walletline.android.presentation.screens.destinations.HomeScreenDestination
import com.walletline.android.presentation.screens.destinations.WalletScreenDestination


enum class BottomBarScreen(
    val direction: DirectionDestination,
    @DrawableRes val icon: Int,
    @StringRes val title: Int

) {

    Wallet(WalletScreenDestination, R.drawable.ic_wallet, R.string.wallet_menu),
    Home(HomeScreenDestination, R.drawable.ic_home, R.string.home_menu),

}