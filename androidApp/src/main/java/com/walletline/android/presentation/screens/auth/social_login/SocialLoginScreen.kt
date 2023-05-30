package com.walletline.android.presentation.screens.auth.social_login

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.walletline.android.presentation.navigation.AuthNavGraph
import com.walletline.android.presentation.screens.auth.pattern.make_pattern.MakePatternContract
import com.walletline.android.presentation.screens.destinations.CreatePatternScreenDestination
import com.walletline.android.presentation.screens.destinations.EmailLoginScreenDestination
import com.walletline.android.presentation.screens.destinations.SocialLoginScreenDestination
import com.walletline.android.presentation.social_auth.apple_auth.AppleAuthUiClient
import com.walletline.android.presentation.social_auth.facebook_auth.FacebookAuthUiClient
import com.walletline.android.presentation.social_auth.google_auth.GoogleAuthUiClient
import com.walletline.android.presentation.util.collectInLaunchedEffect
import com.walletline.android.presentation.util.use
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject


@AuthNavGraph
@Destination
@Composable
fun SocialLoginScreen(
    navigator: DestinationsNavigator,
    viewModel: SocialLoginViewModel = koinViewModel(),
    googleAuthUiClient: GoogleAuthUiClient = koinInject(),
    facebookAuthUiClient: FacebookAuthUiClient = koinInject(),
    appleAuthUiClient: AppleAuthUiClient = koinInject(),
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isClickActive by remember {
        mutableStateOf(true)
    }

    val (state, effectFlow, event) = use(viewModel = viewModel)

    effectFlow.collectInLaunchedEffect { effect ->
        when (effect) {
            SocialLoginContract.Effect.NavigateToEmailLogin -> navigator.navigate(
                EmailLoginScreenDestination
            )

            SocialLoginContract.Effect.NavigateToPattern -> navigator.navigate(CreatePatternScreenDestination)

            is SocialLoginContract.Effect.ShowErrorMessage -> { /* TODO: */ }
        }
    }

    val googleOneTapLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = onResult@{ result ->
            if (result.resultCode == Activity.RESULT_OK) {

                val authTokens = googleAuthUiClient.getTokens(
                    intent = result.data ?: return@onResult
                )
                viewModel.onEvent(SocialLoginContract.Event.SingInWithGoogle(authTokens))

            }
        }
    )



    SocialLoginContent(
        isClickEnable = !state.isLoading && isClickActive,
        onEmailClicked = {
            navigator.navigate(EmailLoginScreenDestination) {
                popUpTo(SocialLoginScreenDestination.route) {
                    inclusive = true
                }
            }
        },
        onGoogleClicked = {
            scope.launch {
                isClickActive = false
                val signInIntentSender = googleAuthUiClient.signIn()
                googleOneTapLauncher.launch(
                    IntentSenderRequest.Builder(
                        intentSender = signInIntentSender ?: return@launch
                    ).build()
                )
            }.invokeOnCompletion {
                isClickActive = true
            }
        },
        onFacebookClicked = {
            scope.launch {
                isClickActive = false
                val facebookLoginResult = facebookAuthUiClient.signIn(context)
                viewModel.onEvent(
                    event = SocialLoginContract.Event.SingInWithFacebook(
                        facebookLoginResult
                    )
                )
            }.invokeOnCompletion {
                isClickActive = true
            }
        },
        onAppleClicked = {
//            isClickActive = false
//            scope.launch {
//                appleAuthUiClient.signIn(context)
//            }.invokeOnCompletion {
//                isClickActive = true
//            }
        },
    )
}