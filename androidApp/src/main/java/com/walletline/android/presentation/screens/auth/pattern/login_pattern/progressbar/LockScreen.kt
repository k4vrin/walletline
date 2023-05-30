package com.walletline.android.presentation.screens.auth.pattern.login_pattern.progressbar

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.walletline.android.presentation.navigation.AuthNavGraph
import com.walletline.android.presentation.screens.auth.pattern.login_pattern.LoginPatternContract
import com.walletline.android.presentation.screens.auth.pattern.login_pattern.LoginPatternViewModel
import com.walletline.android.presentation.screens.destinations.EnterPatternScreenDestination
import com.walletline.android.presentation.screens.destinations.LockScreenDestination
import com.walletline.android.presentation.util.collectInLaunchedEffect
import com.walletline.android.presentation.util.use
import org.koin.androidx.compose.koinViewModel

@AuthNavGraph
@Destination
@Composable
fun LockScreen(
    navigator: DestinationsNavigator,
    viewModel: LoginPatternViewModel = koinViewModel(),
) {
    val (state, effectFlow, _) = use(viewModel = viewModel)

    effectFlow.collectInLaunchedEffect { effect ->
        when (effect) {
            LoginPatternContract.Effect.LockApp -> Unit
            LoginPatternContract.Effect.PatternIsVerified -> Unit
            LoginPatternContract.Effect.LockIsExpired -> {
                navigator.navigate(EnterPatternScreenDestination()) {
                    popUpTo(LockScreenDestination.route) {
                        inclusive = true
                    }
                }
            }
        }
    }
    LockContent(state)
}
