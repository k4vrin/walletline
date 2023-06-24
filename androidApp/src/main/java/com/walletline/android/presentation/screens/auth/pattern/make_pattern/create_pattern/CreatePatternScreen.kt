package com.walletline.android.presentation.screens.auth.pattern.make_pattern.create_pattern

import android.Manifest
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.walletline.android.presentation.navigation.AuthNavGraph
import com.walletline.android.presentation.screens.auth.pattern.make_pattern.MakePatternContract
import com.walletline.android.presentation.screens.auth.pattern.make_pattern.MakePatternViewModel
import com.walletline.android.presentation.screens.destinations.ConfirmPatternScreenDestination
import com.walletline.android.presentation.util.BiometricClient
import com.walletline.android.presentation.util.collectInLaunchedEffect
import com.walletline.android.presentation.util.koinSharedViewModel
import com.walletline.android.presentation.util.use
import com.walletline.domain.model.auth.BiometricError
import com.walletline.domain.model.auth.BiometricType

@AuthNavGraph(start = true)
@Destination
@Composable
fun CreatePatternScreen(
    navigator: DestinationsNavigator,
    navController: NavController,
    navBackStackEntry: NavBackStackEntry,
    viewModel: MakePatternViewModel = navBackStackEntry.koinSharedViewModel(navController = navController),
    biometricClient: BiometricClient = BiometricClient(),
) {

    val biometricPermission =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            rememberPermissionState(permission = Manifest.permission.USE_BIOMETRIC)
        } else {
            null
        }

    val context = LocalContext.current

    val (state, effectFlow, event) = use(viewModel = viewModel)

    effectFlow.collectInLaunchedEffect { effect ->
        when (effect) {
            MakePatternContract.Effect.NavigateToConfirmPattern -> navigator.navigate(
                ConfirmPatternScreenDestination()
            )

            MakePatternContract.Effect.ShowBiometricPrompt -> {
                biometricPermission?.launchPermissionRequest()
                val isGranted = biometricPermission?.status?.isGranted ?: true
                if (isGranted) {
                    val biometricResult = biometricClient.showBiometricPrompt(context)
                    when (biometricResult.isSuccessful) {
                        true -> viewModel.onEvent(
                            MakePatternContract.Event.FingerFaceSuccessful(
                                type = biometricResult.biometricType ?: BiometricType.Unknown
                            )
                        )

                        false -> viewModel.onEvent(
                            MakePatternContract.Event.FingerFaceUnSuccessful(
                                error = biometricResult.error ?: BiometricError.NoBiometric
                            )
                        )
                    }
                }
            }

            MakePatternContract.Effect.SkipMakePattern -> {
                // FIXME: Should navigate to home
                navigator.popBackStack()
            }

            MakePatternContract.Effect.BiometricSuccess -> {
                // FIXME: Should navigate to home
                navigator.popBackStack()
            }

            MakePatternContract.Effect.ConfirmPatternSuccessful -> Unit
            is MakePatternContract.Effect.ConfirmPatternUnSuccessful -> Unit
            MakePatternContract.Effect.RetryPattern -> Unit
        }
    }

    CreatePatternContent(
        state = state,
        onEvent = event,
    )
}


