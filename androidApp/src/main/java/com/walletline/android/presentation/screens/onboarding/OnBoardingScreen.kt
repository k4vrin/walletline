package com.walletline.android.presentation.screens.onboarding


import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.walletline.android.presentation.screens.destinations.SocialLoginScreenDestination


@RootNavGraph(start = true)
@Destination
@Composable
fun OnBoardingScreen(
    navigator: DestinationsNavigator
) {
    OnBoardingContent() {
        navigator.navigate(SocialLoginScreenDestination)
    }
}

