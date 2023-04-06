package com.walletline.android.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.walletline.android.presentation.screens.NavGraphs
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.transition.WalletLineTransitionAnimation
import com.walletline.domain.use_case.DummyUseCase
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class MainActivity : ComponentActivity() {
    // FIXME: Just for showcase. will be deleted
    private val useCase: DummyUseCase by inject()
    // FIXME: Just for showcase. will be deleted

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make status bar be part of component window. edge to edge window
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // FIXME: Just for showcase. will be deleted
        lifecycleScope.launch {
            val dummyToken = useCase.execute()
            Log.d("MainActivity", "onCreate: $dummyToken")
        }
        // FIXME: Just for showcase. will be deleted


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

        }

    }
}

