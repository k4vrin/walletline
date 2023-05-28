package com.walletline.android.presentation.screens.auth.social_login

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.walletline.android.presentation.navigation.AuthNavGraph
import com.walletline.android.presentation.screens.destinations.EmailLoginScreenDestination
import org.koin.androidx.compose.koinViewModel


@Destination
@Composable
fun SocialLoginScreen(
    navigator: DestinationsNavigator,
    viewModel: SocialLoginViewModel = koinViewModel()
) {

    SocialLoginContent(
        onEmailClicked = {
            navigator.navigate(EmailLoginScreenDestination)
        },
        onGoogleClicked = {},
        onFacebookClicked = {},
        onAppleClicked = {},
    )
}