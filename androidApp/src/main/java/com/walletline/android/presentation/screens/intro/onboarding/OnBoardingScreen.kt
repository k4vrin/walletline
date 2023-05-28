package com.walletline.android.presentation.screens.intro.onboarding


import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.walletline.android.presentation.navigation.IntroNavGraph
import com.walletline.android.presentation.screens.destinations.EmailLoginScreenDestination
import com.walletline.android.presentation.screens.intro.IntroContract
import com.walletline.android.presentation.screens.intro.IntroViewModel
import com.walletline.android.presentation.util.collectInLaunchedEffect
import com.walletline.android.presentation.util.use
import org.koin.androidx.compose.koinViewModel

@IntroNavGraph
@Destination
@Composable
fun OnBoardingScreen(
    navigator: DestinationsNavigator,
    viewModel: IntroViewModel = koinViewModel(),

    ) {

    val (state, effectFlow, event) = use(viewModel = viewModel)

    effectFlow.collectInLaunchedEffect { effect ->
        when (effect) {
            IntroContract.Effect.UserOnBoarded -> navigator.navigate(EmailLoginScreenDestination())
            is IntroContract.Effect.Navigate -> Unit
        }
    }
    OnBoardingContent(state = state, onEvent = event)
}

