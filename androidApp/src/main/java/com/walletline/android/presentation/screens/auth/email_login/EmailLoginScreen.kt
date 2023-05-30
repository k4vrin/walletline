package com.walletline.android.presentation.screens.auth.email_login

import android.Manifest
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.walletline.android.di.util.KoinAndroidQualifiers
import com.walletline.android.presentation.navigation.AuthNavGraph
import com.walletline.android.presentation.notification.NotificationService
import com.walletline.android.presentation.screens.auth.verify_email.VerifyEmailNavArg
import com.walletline.android.presentation.screens.destinations.EmailLoginScreenDestination
import com.walletline.android.presentation.screens.destinations.SocialLoginScreenDestination
import com.walletline.android.presentation.screens.destinations.VerifyEmailScreenDestination
import com.walletline.android.presentation.util.collectInLaunchedEffect
import com.walletline.android.presentation.util.use
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@AuthNavGraph
@Destination
@Composable
fun EmailLoginScreen(
    navigator: DestinationsNavigator,
    otpNotification: NotificationService = koinInject(qualifier = KoinAndroidQualifiers.OtpNotification),
    viewModel: EmailLoginViewModel = koinViewModel(),
) {

    val context = LocalContext.current

    // Notification Permission. Asking for permission here is for development stage only
    val notificationPermissionState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    } else {
        null
    }

    LaunchedEffect(Unit) {
        notificationPermissionState?.launchPermissionRequest()
    }

    val (state, effectFlow, event) = use(viewModel = viewModel)

    effectFlow.collectInLaunchedEffect { effect ->
        when (effect) {
            EmailLoginContract.Effect.EnterBySocial -> navigator.navigate(SocialLoginScreenDestination) {
                popUpTo(EmailLoginScreenDestination.route) {
                    inclusive = true
                }
            }

            is EmailLoginContract.Effect.Error -> {/* FIXME: Showing error to user, such as connection issue, server error and etc through snackbar or toast */
            }

            is EmailLoginContract.Effect.RegisterSuccessful -> {

                if (notificationPermissionState == null || notificationPermissionState.status.isGranted) {
                    otpNotification.showNotification(
                        context = context,
                        title = "Verification Code",
                        content = "Your verification code is ${effect.otp}"
                    )
                }


                navigator.navigate(VerifyEmailScreenDestination(navArgs = VerifyEmailNavArg(email = effect.email)))
            }
        }
    }

    EmailLoginContent(
        state = state,
        onEvent = event
    )

}


