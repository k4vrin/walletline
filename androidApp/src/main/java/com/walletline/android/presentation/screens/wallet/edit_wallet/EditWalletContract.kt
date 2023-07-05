package com.walletline.android.presentation.screens.wallet.edit_wallet

import com.walletline.android.presentation.screens.wallet.WalletContract
import com.walletline.android.presentation.screens.wallet.setup_wallet.SetupWalletContract
import com.walletline.android.presentation.util.UnidirectionalViewModel
import com.walletline.domain.model.auth.SocialSignInError
import com.walletline.domain.model.wallet.CreateWalletError
import com.walletline.domain.model.wallet.Wallet
import com.walletline.domain.model.wallet.WalletLine
import com.walletline.presentation.screens.wallet.EditWalletState

interface EditWalletContract :
    UnidirectionalViewModel<EditWalletState, EditWalletContract.Effect, EditWalletContract.Event> {
    sealed interface Event {

        object OnSaveButtonCLicked : Event
        data class OnWalletTitleChanged(val title: String) : Event
        data class OnWalletBalanceChanged(val balance: String) : Event
        data class OnWalletDescriptionChanged(val description: String) : Event
        data class OnCreateLineClicked(val lines:List<WalletLine>): Event
        data class OnDeleteWalletClicked(val walletId:String): Event

        object OnBackButtonClicked : Event

    }

    sealed interface Effect {
        object NavigateBack : Effect
        data class ReceiveWalletSuccessful(val wallet: Wallet?) : Effect
        data class ShowWalletError(val receiveWalletError: SocialSignInError) : Effect
        data class SaveWalletFailed(val SaveWalletError: String?) : Effect
        object WalletTitleIsEmpty : Effect
        object WalletBalanceIsEmpty : Effect
        object SaveWalletSuccessful : Effect

        object CreateWalletSuccessful : Effect
        data class CreateWalletFailed(val CreateWalletError: CreateWalletError) :
            Effect

        object CreateLineSuccessful : Effect
        object DeleteWalletSuccessful : Effect
        data class DeleteWalletFailed(val DeleteWalletError: String?) : Effect


    }
}