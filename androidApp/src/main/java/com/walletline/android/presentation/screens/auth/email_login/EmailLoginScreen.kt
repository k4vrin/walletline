package com.walletline.android.presentation.screens.auth.email_login

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.walletline.android.presentation.navigation.AuthNavGraph
import com.walletline.android.presentation.screens.destinations.SocialLoginScreenDestination
import com.walletline.android.presentation.util.collectInLaunchedEffect
import com.walletline.android.presentation.util.use
import org.koin.androidx.compose.koinViewModel

@AuthNavGraph
@Destination
@Composable
fun EmailLoginScreen(
    navigator: DestinationsNavigator,
    viewModel: EmailLoginViewModel = koinViewModel(),
) {

    val (state, effectFlow, event) = use(viewModel = viewModel)

    effectFlow.collectInLaunchedEffect { effect ->
        when (effect) {
            EmailLoginContract.Effect.EnterBySocial -> navigator.navigate(SocialLoginScreenDestination)
            is EmailLoginContract.Effect.Error -> {/* FIXME: Add Error */}
            EmailLoginContract.Effect.RegisterSuccessful -> { /* TODO: Navigate to Verify otp */}
        }
    }

    EmailLoginContent(
        state = state,
        onEvent = event
    )

}


