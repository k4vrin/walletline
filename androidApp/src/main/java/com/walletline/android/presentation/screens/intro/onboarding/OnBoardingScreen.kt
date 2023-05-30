package com.walletline.android.presentation.screens.intro.onboarding


import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.walletline.android.presentation.navigation.IntroNavGraph
import com.walletline.android.presentation.screens.NavGraphs
import com.walletline.android.presentation.screens.destinations.SocialLoginScreenDestination
import com.walletline.android.presentation.screens.intro.IntroContract
import com.walletline.android.presentation.screens.intro.IntroViewModel
import com.walletline.android.presentation.util.collectInLaunchedEffect
import com.walletline.android.presentation.util.koinSharedViewModel
import com.walletline.android.presentation.util.use

@IntroNavGraph
@Destination
@Composable
fun OnBoardingScreen(
    navigator: DestinationsNavigator,
    navBackStackEntry: NavBackStackEntry,
    navController: NavController,
    viewModel: IntroViewModel = navBackStackEntry.koinSharedViewModel(navController = navController),
) {

    val (state, effectFlow, event) = use(viewModel = viewModel)

    effectFlow.collectInLaunchedEffect { effect ->
        when (effect) {
            IntroContract.Effect.UserOnBoarded -> navigator.navigate(SocialLoginScreenDestination()) {
                popUpTo(NavGraphs.intro.route) {
                    inclusive = true
                }
            }
            is IntroContract.Effect.Navigate -> Unit
        }
    }
    OnBoardingContent(state = state, onEvent = event)
}

