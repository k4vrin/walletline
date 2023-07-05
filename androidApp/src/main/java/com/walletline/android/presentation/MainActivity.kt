package com.walletline.android.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.spec.NavHostEngine
import com.walletline.android.presentation.screens.NavGraphs
import com.walletline.android.presentation.screens.destinations.EditWalletScreenDestination
import com.walletline.android.presentation.screens.destinations.WalletScreenDestination
import com.walletline.android.presentation.screens.wallet.WalletContract
import com.walletline.android.presentation.screens.wallet.components.bottombar.WalletBottomBar
import com.walletline.android.presentation.screens.wallet.components.topbar.WalletTopBar
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding
import com.walletline.android.presentation.theme.transition.WalletLineTransitionAnimation
import com.walletline.android.presentation.util.BackPressHandler


class MainActivity : FragmentActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make status bar be part of component window. edge to edge window
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            WalletLineTheme {
                val engine = rememberAnimatedNavHostEngine(
                    rootDefaultAnimations = WalletLineTransitionAnimation.default
                )
                var navController = engine.rememberNavController()
                var showBottomBar = rememberSaveable { mutableStateOf(true) }
                val navBackStackEntry = navController.currentBackStackEntryAsState()
                showBottomBar.value = when (navBackStackEntry.value?.destination?.route) {
                    WalletScreenDestination.baseRoute -> true // here too
                    else -> false // in all other cases show bottom bar
                }
                Scaffold(
                    modifier = Modifier
                    . background (
                        MaterialTheme.customColor.neutrals.one
                        ),
                contentWindowInsets = WindowInsets.navigationBars,
                bottomBar = { if (showBottomBar.value){
                    WalletBottomBar(
                        navController = navController,
                        modifier = Modifier
                            .navigationBarsPadding()
                            .padding(
                                bottom = MaterialTheme.padding.medium,
                                start = MaterialTheme.padding.small,
                                end = MaterialTheme.padding.small,
                            ),
                    )
                }},

                ) { paddingValues ->
                DestinationsNavHost(
                    dependenciesContainerBuilder = {
                        dependency(paddingValues)
                    },
                    navGraph = NavGraphs.wallet,
                    engine = engine,
                    navController = navController,
                    startRoute = NavGraphs.wallet.startRoute,
                )

            }
            }

            BackPressHandler()

        }
    }
}

