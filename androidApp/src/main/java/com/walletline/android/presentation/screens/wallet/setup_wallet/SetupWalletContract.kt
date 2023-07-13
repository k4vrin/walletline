package com.walletline.android.presentation.screens.wallet.setup_wallet

import com.walletline.android.presentation.util.UnidirectionalViewModel
import com.walletline.domain.model.auth.SocialSignInError
import com.walletline.domain.model.wallet.CreateWalletError
import com.walletline.domain.model.wallet.WalletLine
import com.walletline.presentation.screens.wallet.SetupWalletState

interface SetupWalletContract :
    UnidirectionalViewModel<SetupWalletState, SetupWalletContract.Effect, SetupWalletContract.Event> {
    sealed interface Event {
        object OnSaveButtonCLicked : Event
        data class OnCreateLineClicked(val lines:List<WalletLine>): Event
        data class OnWalletTitleChanged(val title:String) : Event
        data class OnWalletBalanceChanged(val balance:String) : Event
        data class OnWalletDescriptionChanged(val description :String): Event
        object OnBackButtonClicked : Event

    }

    sealed interface Effect {
        object NavigateBack: Effect
        object NavigateToSetupLine: Effect
        object WalletTitleIsEmpty : Effect
        object WalletBalanceIsEmpty : Effect
        object CreateWalletSuccessful : Effect
        data class CreateWalletFailed(val CreateWalletError: CreateWalletError): Effect
        object CreateLineSuccessful : Effect
    }
}