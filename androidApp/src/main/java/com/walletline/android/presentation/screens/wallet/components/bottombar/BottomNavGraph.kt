package com.walletline.android.presentation.screens.wallet.components.bottombar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.walletline.android.presentation.screens.wallet.WalletScreen
import com.walletline.android.presentation.screens.wallet.WalletViewModel
import com.walletline.presentation.screens.wallet.WalletState


@Composable
fun BottomNavGraph(
    viewModel: WalletViewModel,
    state: WalletState,
    navigator: DestinationsNavigator,
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.direction.toString()
    ) {
        composable(route = BottomBarScreen.Home.direction.toString())
        {
          /*  WalletScreen(
                    viewModel = viewModel,
                )*/
        }
        composable(route = BottomBarScreen.Wallet.toString())
        {
        }

    }
}