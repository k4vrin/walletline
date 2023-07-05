package com.walletline.android.presentation.screens.wallet.setup_wallet

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.walletline.android.R
import com.walletline.android.presentation.navigation.WalletNavGraph
import com.walletline.android.presentation.util.collectInLaunchedEffect
import com.walletline.android.presentation.util.koinSharedViewModel
import com.walletline.android.presentation.util.use


@WalletNavGraph
@Destination
@Composable
fun SetupWalletScreen(
    navigator: DestinationsNavigator,
    navController: NavController,
    navBackStackEntry: NavBackStackEntry,
    viewModel: SetupWalletViewModel = navBackStackEntry.koinSharedViewModel(navController = navController),
) {

    val emptyTitleError = stringResource(
        id = R.string.empty_title_error
    )
    val emptyBalanceError = stringResource(
        id = R.string.empty_balance_error
    )

    val (state, effectFlow, event) = use(viewModel = viewModel)
    val context = LocalContext.current
    val walletTitleErrorMessage = remember { mutableStateOf("") }
    val walletBalanceErrorMessage = remember { mutableStateOf("") }


    effectFlow.collectInLaunchedEffect { effect ->
        when (effect) {
            SetupWalletContract.Effect.CreateLineSuccessful -> TODO()
            is SetupWalletContract.Effect.CreateWalletFailed -> {
                showToastMessage(
                    context = context,
                    message = effect.CreateWalletError.toString()
                )
            }
            SetupWalletContract.Effect.CreateWalletSuccessful -> showToastMessage(
                context = context,
                message = "wallet created successfully"
            )
            SetupWalletContract.Effect.NavigateBack -> navigator.popBackStack()
            SetupWalletContract.Effect.NavigateToSetupLine -> TODO()
            SetupWalletContract.Effect.WalletBalanceIsEmpty -> {
                walletTitleErrorMessage.value =
                    emptyTitleError
            }
            SetupWalletContract.Effect.WalletTitleIsEmpty -> {
                walletBalanceErrorMessage.value =
                    emptyBalanceError
            }
        }
    }

    SetupWalletContent(
        state = state,
        onWalletTitleChanged = { viewModel.onEvent(SetupWalletContract.Event.OnWalletTitleChanged(it)) },
        onWalletBalanceChanged = {
            viewModel.onEvent(
                SetupWalletContract.Event.OnWalletBalanceChanged(
                    it
                )
            )
        },
        onWalletDescriptionChanged = {
            viewModel.onEvent(
                SetupWalletContract.Event.OnWalletDescriptionChanged(
                    it
                )
            )
        },
        walletTitleErrorMessage = walletTitleErrorMessage,
        walletBalanceErrorMessage = walletBalanceErrorMessage
    )
}

private fun showToastMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}