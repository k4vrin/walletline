package com.walletline.android.presentation.screens.wallet.edit_wallet

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.walletline.android.R
import com.walletline.android.presentation.navigation.WalletNavGraph
import com.walletline.android.presentation.screens.auth.verify_email.VerifyEmailNavArg
import com.walletline.android.presentation.screens.wallet.WalletContract
import com.walletline.android.presentation.screens.wallet.WalletViewModel
import com.walletline.android.presentation.screens.wallet.components.topbar.WalletTopBar
import com.walletline.android.presentation.screens.wallet.setup_wallet.SetupWalletContract
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.util.collectInLaunchedEffect
import com.walletline.android.presentation.util.use
import org.koin.androidx.compose.koinViewModel

@WalletNavGraph
@Destination(navArgsDelegate = EditWalletNavArg::class)
@Composable
fun EditWalletScreen(
    navigator: DestinationsNavigator,
    viewModel: EditWalletViewModel = koinViewModel(),
    paddingValues: PaddingValues,
) {

    val saveWalletMessage = stringResource(id = R.string.save_wallet_message)
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

    var topBarTextSrc = R.string.edit_wallet
    if (!state.editWalletMode) {
        topBarTextSrc = R.string.setup_wallet
    }

    val topBarText = remember {
        mutableStateOf(topBarTextSrc)
    }

    effectFlow.collectInLaunchedEffect { effect ->
        when (effect) {
            EditWalletContract.Effect.NavigateBack -> {
                navigator.popBackStack()
            }
            is EditWalletContract.Effect.ReceiveWalletSuccessful -> TODO()
            is EditWalletContract.Effect.ShowWalletError -> {
                showToastMessage(context = context, message = effect.receiveWalletError.toString())
                navigator.popBackStack()
            }
            is EditWalletContract.Effect.SaveWalletFailed -> showToastMessage(
                context = context,
                message = effect.SaveWalletError.toString()
            )
            EditWalletContract.Effect.SaveWalletSuccessful -> showToastMessage(
                context = context,
                message = saveWalletMessage
            )
            EditWalletContract.Effect.WalletBalanceIsEmpty -> {
                walletBalanceErrorMessage.value =
                    emptyBalanceError
            }
            EditWalletContract.Effect.WalletTitleIsEmpty -> {
                walletTitleErrorMessage.value =
                    emptyTitleError
            }
            is EditWalletContract.Effect.CreateWalletFailed -> {
                showToastMessage(
                    context = context,
                    message = effect.CreateWalletError.toString()
                )
            }
            EditWalletContract.Effect.CreateWalletSuccessful -> showToastMessage(
                context = context,
                message = "Wallet created successfully"
            )
            EditWalletContract.Effect.CreateLineSuccessful -> TODO()
            is EditWalletContract.Effect.DeleteWalletFailed -> {
                showToastMessage(
                    context = context,
                    message = effect.DeleteWalletError.toString()
                )
            }
            EditWalletContract.Effect.DeleteWalletSuccessful -> {
                showToastMessage(
                    context = context,
                    message = "Wallet Deleted Successfully"
                )
            }
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.customColor.neutrals.one),
        topBar = {
            WalletTopBar(
                text = topBarText.value,
                onBackButtonClicked = {
                    viewModel.onEvent(
                        event = EditWalletContract.Event.OnBackButtonClicked
                    )
                },
                showMenu = false
            )
        },
    ) {
        EditWalletContent(
            state = state,
            onSaveButtonClicked = {
                viewModel.onEvent(
                    event = EditWalletContract.Event.OnSaveButtonCLicked
                )
            },
            onWalletTitleChanged = {
                viewModel.onEvent(
                    event = EditWalletContract.Event.OnWalletTitleChanged(it)
                )
            },
            onWalletBalanceChanged = {
                viewModel.onEvent(
                    event = EditWalletContract.Event.OnWalletBalanceChanged(it)
                )
            },
            onWalletDescriptionChanged = {
                viewModel.onEvent(
                    event = EditWalletContract.Event.OnWalletDescriptionChanged(it)
                )
            },
            walletTitleErrorMessage = walletTitleErrorMessage,
            walletBalanceErrorMessage = walletBalanceErrorMessage,
            paddingValues = it,
            onDeleteWalletClicked = {
                viewModel.onEvent(
                    event = EditWalletContract.Event.OnDeleteWalletClicked(it)
                )
            }
        )
    }
}

private fun showToastMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}