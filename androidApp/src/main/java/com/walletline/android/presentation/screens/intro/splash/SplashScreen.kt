package com.walletline.android.presentation.screens.intro.splash

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.walletline.android.presentation.navigation.IntroNavGraph
import com.walletline.android.presentation.screens.destinations.EmailLoginScreenDestination
import com.walletline.android.presentation.screens.destinations.EnterPatternScreenDestination
import com.walletline.android.presentation.screens.destinations.OnBoardingScreenDestination
import com.walletline.android.presentation.screens.intro.IntroContract
import com.walletline.android.presentation.screens.intro.IntroViewModel
import com.walletline.android.presentation.util.collectInLaunchedEffect
import com.walletline.android.presentation.util.use
import com.walletline.domain.model.UserCondition
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
                UserCondition.FirstTime -> navigator.navigate(OnBoardingScreenDestination())
                UserCondition.OnBoarded -> navigator.navigate(EmailLoginScreenDestination())
                UserCondition.SignedInWithPattern -> navigator.navigate(
                    EnterPatternScreenDestination()
                )

                UserCondition.SignedInWithFinger -> navigator.navigate(EnterPatternScreenDestination())
                UserCondition.SignedIn -> {/* TODO: Home */
                }
            }
        }
    }

    SplashContent(state = state, onEvent = event)

}






