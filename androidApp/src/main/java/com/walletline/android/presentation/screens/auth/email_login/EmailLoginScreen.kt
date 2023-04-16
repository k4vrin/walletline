package com.walletline.android.presentation.screens.auth.email_login

import androidx.compose.runtime.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.walletline.android.presentation.navigation.AuthNavGraph
import com.walletline.android.presentation.screens.destinations.SocialLoginScreenDestination
import com.walletline.android.presentation.screens.destinations.VerifyNumberScreenDestination

@AuthNavGraph
@Destination
@Composable
fun EmailLoginScreen(
    navigator: DestinationsNavigator,
) {

    var emailText by remember { mutableStateOf("") }
    var errorMessage: String? by remember { mutableStateOf(null) }

    EmailLoginContent(
        emailText = emailText,
        emailErrorMessage = errorMessage,
        onEmailChange = {
            emailText = it
        },
        onSocialClicked = {
            navigator.navigate(SocialLoginScreenDestination)
        },
        onContinueClicked = {
            // FIXME: Error show case, error handling should happen in shared module
            if (emailText.length < 10) {
                errorMessage = "Error"
            } else {
                errorMessage = null
                navigator.navigate(VerifyNumberScreenDestination)
            }

        }
    )

}


