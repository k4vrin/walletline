package com.walletline.android.presentation.screens.intro.splash

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.walletline.android.presentation.navigation.IntroNavGraph
import com.walletline.android.presentation.screens.NavGraphs
import com.walletline.android.presentation.screens.destinations.EnterPatternScreenDestination
import com.walletline.android.presentation.screens.destinations.OnBoardingScreenDestination
import com.walletline.android.presentation.screens.destinations.SocialLoginScreenDestination
import com.walletline.android.presentation.screens.destinations.SplashScreenDestination
import com.walletline.android.presentation.screens.intro.IntroContract
import com.walletline.android.presentation.screens.intro.IntroViewModel
import com.walletline.android.presentation.util.collectInLaunchedEffect
import com.walletline.android.presentation.util.use
import com.walletline.domain.model.auth.UserCondition
import org.koin.androidx.compose.koinViewModel

@IntroNavGraph(start = true)
@Destination
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator,
    viewModel: IntroViewModel = koinViewModel(),
) {

    val (state, effectFlow, event) = use(viewModel = viewModel)

    effectFlow.collectInLaunchedEffect { effect ->
        when (effect) {
            IntroContract.Effect.UserOnBoarded -> Unit
            is IntroContract.Effect.Navigate -> when (effect.userCondition) {
                UserCondition.FirstTime -> navigator.navigate(OnBoardingScreenDestination()) {
                    popUpTo(SplashScreenDestination.route) {
                        inclusive = true
                    }
                }
                UserCondition.OnBoarded -> navigator.navigate(SocialLoginScreenDestination()) {
                    popUpTo(NavGraphs.intro.route) {
                        inclusive = true
                    }
                }

                UserCondition.SignedInWithPattern -> navigator.navigate(
                    EnterPatternScreenDestination()
                ) {
                    popUpTo(NavGraphs.intro.route) {
                        inclusive = true
                    }
                }

                UserCondition.SignedInWithFinger -> navigator.navigate(EnterPatternScreenDestination()) {
                    popUpTo(NavGraphs.intro.route) {
                        inclusive = true
                    }
                }

                UserCondition.SignedIn -> {/* TODO: Home */
                }
            }
        }
    }

    SplashContent(state = state, onEvent = event)

}






