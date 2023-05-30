package com.walletline.android.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.walletline.android.presentation.screens.NavGraphs
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.transition.WalletLineTransitionAnimation
import com.walletline.android.presentation.util.BackPressHandler


class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make status bar be part of component window. edge to edge window
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            WalletLineTheme {
                val engine = rememberAnimatedNavHostEngine(
                    rootDefaultAnimations = WalletLineTransitionAnimation.default
                )

                Scaffold(
                    modifier = Modifier,
                    contentWindowInsets = WindowInsets.navigationBars
                ) { paddingValues ->
                    DestinationsNavHost(
                        modifier = Modifier
                            .padding(paddingValues),
                        navGraph = NavGraphs.root,
                        engine = engine,
                        navController = engine.rememberNavController()
                    )
                }
            }

            BackPressHandler()

        }
    }
}