package com.walletline.android.presentation.screens.auth.pattern.make_pattern.confirm_pattern


import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.walletline.android.presentation.navigation.AuthNavGraph
import com.walletline.android.presentation.screens.auth.pattern.make_pattern.MakePatternContract
import com.walletline.android.presentation.screens.auth.pattern.make_pattern.MakePatternViewModel
import com.walletline.android.presentation.util.collectInLaunchedEffect
import com.walletline.android.presentation.util.koinSharedViewModel
import com.walletline.android.presentation.util.use

@AuthNavGraph
@Destination
@Composable
fun ConfirmPatternScreen(
    navigator: DestinationsNavigator,
    navController: NavController,
    navBackStackEntry: NavBackStackEntry,
    viewModel: MakePatternViewModel = navBackStackEntry.koinSharedViewModel(navController = navController),
) {

    val context = LocalContext.current

    val (state, effectFlow, event) = use(viewModel = viewModel)

    effectFlow.collectInLaunchedEffect { effect ->
        when (effect) {
            MakePatternContract.Effect.ConfirmPatternSuccessful -> {
                /* TODO: Home Screen */
            }

            MakePatternContract.Effect.RetryPattern -> {
                navigator.popBackStack()
            }

            MakePatternContract.Effect.SkipMakePattern -> {
                /* TODO: Home Screen */
            }

            is MakePatternContract.Effect.ConfirmPatternUnSuccessful -> {
               Toast.makeText(context, "Patterns are not same as each other", Toast.LENGTH_SHORT)
                   .show()
            }

            else -> Unit
        }
    }

    ConfirmPatternContent(
        state = state,
        onEvent = event,
    )
}

