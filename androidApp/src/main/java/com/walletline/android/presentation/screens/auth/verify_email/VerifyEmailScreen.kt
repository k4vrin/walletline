package com.walletline.android.presentation.screens.auth.verify_email

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.walletline.android.di.util.KoinAndroidQualifiers
import com.walletline.android.presentation.navigation.AuthNavGraph
import com.walletline.android.presentation.notification.NotificationService
import com.walletline.android.presentation.screens.destinations.CreatePatternScreenDestination
import com.walletline.android.presentation.util.collectInLaunchedEffect
import com.walletline.android.presentation.util.use
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@AuthNavGraph
@Destination(navArgsDelegate = VerifyEmailNavArg::class)
@Composable
fun VerifyEmailScreen(
    viewModel: VerifyEmailViewModel = koinViewModel(),
    otpNotification: NotificationService = koinInject(qualifier = KoinAndroidQualifiers.OtpNotification),
    navigator: DestinationsNavigator,
) {
    val context = LocalContext.current

    val (state, effectFlow, event) = use(viewModel = viewModel)
    effectFlow.collectInLaunchedEffect { effect ->
        when (effect) {
            is VerifyEmailContract.Effect.ShowError -> {/* FIXME: Add Error*/ }
            VerifyEmailContract.Effect.NavigateToEmail -> navigator.popBackStack()
            VerifyEmailContract.Effect.ShowPolicy -> {/* TODO:  */}
            VerifyEmailContract.Effect.ShowTerms -> {/* TODO:  */}
            VerifyEmailContract.Effect.VerifySuccessful -> navigator.navigate(
                CreatePatternScreenDestination
            )
            is VerifyEmailContract.Effect.ResendOtpSuccess -> otpNotification.showNotification(
                context = context,
                title = "Verification Code",
                content = "Your Verification code is: ${effect.otp}"
            )
        }
    }

    VerifyEmailContent(state = state, onEvent = event)
}

