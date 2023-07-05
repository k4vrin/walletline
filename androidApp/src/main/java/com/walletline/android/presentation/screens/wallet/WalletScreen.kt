package com.walletline.android.presentation.screens.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.walletline.android.R
import com.walletline.android.presentation.navigation.WalletNavGraph
import com.walletline.android.presentation.screens.auth.verify_email.VerifyEmailNavArg
import com.walletline.android.presentation.screens.destinations.EditWalletScreenDestination
import com.walletline.android.presentation.screens.destinations.PartnersListScreenDestination
import com.walletline.android.presentation.screens.destinations.VerifyEmailScreenDestination
import com.walletline.android.presentation.screens.wallet.components.topbar.WalletTopBar
import com.walletline.android.presentation.screens.wallet.edit_wallet.EditWalletNavArg
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.util.collectInLaunchedEffect
import com.walletline.android.presentation.util.use
import org.koin.androidx.compose.koinViewModel


@WalletNavGraph(start = true)
@Destination
@Composable
fun WalletScreen(
    navigator: DestinationsNavigator,
    paddingValues: PaddingValues,
    viewModel: WalletViewModel = koinViewModel(),
) {


    val (state, effectFlow, event) = use(viewModel = viewModel)

    effectFlow.collectInLaunchedEffect { effect ->
        when (effect) {
            WalletContract.Effect.NavigateToLineDetailScreen -> {

            }
            WalletContract.Effect.WalletChanged -> {

            }
            WalletContract.Effect.ShowLines -> {
            }

            WalletContract.Effect.ShowTransactions -> {
            }

            WalletContract.Effect.ShowPartnersList -> {
                navigator.navigate(PartnersListScreenDestination)
            }
            is WalletContract.Effect.NavigateToEditWalletScreen -> {
                navigator.navigate(EditWalletScreenDestination(navArgs = EditWalletNavArg(walletId = effect.walletId)))
            }

            WalletContract.Effect.NavigateToHomeScreen -> {
                navigator.popBackStack()
            }
            WalletContract.Effect.NavigateToLineSetupScreen -> {
                // navigate to line setup
            }
            WalletContract.Effect.LineCreated -> TODO()
            WalletContract.Effect.NavigateToSetupWalletScreen -> {
                navigator.navigate(EditWalletScreenDestination(navArgs = EditWalletNavArg(walletId = "")))
            }
        }
    }
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.customColor.neutrals.one),
        topBar = {
            WalletTopBar(
                onEditWalletClicked = {
                    viewModel.onEvent(
                        event = WalletContract.Event.OnEditWalletClicked(state.walletId!!)
                    )
                }, onBackButtonClicked = {
                    viewModel.onEvent(
                        event = WalletContract.Event.OnBackButtonClicked
                    )
                },
            text = R.string.wallets,
            showMenu = true)
        },
    ) { it ->
        WalletContent(
            state = state,
            modifier = Modifier.padding(it),
            onCreateLineClicked = {
                viewModel.onEvent(
                    event = WalletContract.Event.OnCreateLineClicked(
                        walletId = state.walletId!!
                    )
                )
            },
            onWalletChanged = {
                viewModel.onEvent(
                    event = WalletContract.Event.OnWalletChanged(
                        walletId = state.walletId!!
                    )
                )
            },
            paddingValues = it,
            scrollState = scrollState,
            onCreateWalletClicked = {
                WalletContract.Event.OnCreateWalletClicked
            }
        )
    }
}
