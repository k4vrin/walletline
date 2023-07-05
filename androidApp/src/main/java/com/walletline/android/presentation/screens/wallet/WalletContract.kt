package com.walletline.android.presentation.screens.wallet

import com.walletline.android.presentation.util.UnidirectionalViewModel
import com.walletline.presentation.screens.wallet.WalletState

interface WalletContract :
    UnidirectionalViewModel<WalletState, WalletContract.Effect, WalletContract.Event> {
    sealed interface Event {
        data class OnLineDetailClicked(val lineId: Int, val walletId: String) : Event
        data class OnLinesClicked( val walletId: String) : Event
        data class OnCreateLineClicked( val walletId: String) : Event
        data class OnEditWalletClicked( val walletId: String) : Event
        data class OnWalletChanged( val walletId: String) : Event
        data class OnViewAllPartnersClicked( val walletId: String) : Event
        object OnCreateWalletClicked : Event
        object OnTransactionsClicked : Event
        object OnBackButtonClicked : Event

    }

    sealed interface Effect {
        object NavigateToLineDetailScreen : Effect
        object ShowLines : Effect
        object ShowTransactions : Effect
        object LineCreated : Effect
        object NavigateToHomeScreen : Effect
        object WalletChanged : Effect
        object ShowPartnersList : Effect
        data class NavigateToEditWalletScreen(val walletId:String) : Effect
        object NavigateToSetupWalletScreen : Effect
        object NavigateToLineSetupScreen : Effect


    }
}