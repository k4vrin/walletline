package com.walletline.android.presentation.screens.wallet.edit_wallet


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walletline.android.presentation.screens.auth.verify_email.VerifyEmailNavArg
import com.walletline.android.presentation.screens.navArgs
import com.walletline.android.presentation.screens.wallet.WalletContract
import com.walletline.android.presentation.screens.wallet.setup_wallet.SetupWalletContract
import com.walletline.domain.model.LineData
import com.walletline.domain.model.PartnerData
import com.walletline.domain.model.auth.SocialSignInError
import com.walletline.domain.model.wallet.Wallet
import com.walletline.domain.use_case.wallet.WalletUseCase
import com.walletline.domain.util.Resource
import com.walletline.presentation.screens.wallet.EditWalletState
import com.walletline.presentation.screens.wallet.WalletState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class EditWalletViewModel(
    savedStateHandle: SavedStateHandle,
    private val walletUseCase: WalletUseCase,
) : ViewModel(), EditWalletContract {

    private val _state = MutableStateFlow(EditWalletState())

    override val state: StateFlow<EditWalletState> = _state.asStateFlow()

    private val effectChannel = Channel<EditWalletContract.Effect>()

    override val effect: Flow<EditWalletContract.Effect> = effectChannel.receiveAsFlow()

    private val clickState = Channel<EditWalletContract.Event>()

    init {
        clickState
            .receiveAsFlow()
            .debounce(400)
            .onEach { event ->
                when (event) {
                    is EditWalletContract.Event.OnSaveButtonCLicked -> Unit

                    EditWalletContract.Event.OnBackButtonClicked -> {
                        effectChannel.trySend(EditWalletContract.Effect.NavigateBack)
                    }
                    is EditWalletContract.Event.OnWalletTitleChanged -> {
                        _state.update { mState ->
                            mState.copy(walletTitle = event.title)
                        }
                    }
                    is EditWalletContract.Event.OnWalletBalanceChanged -> {
                        _state.update { mState ->
                            mState.copy(walletBalance = event.balance)
                        }
                    }
                    is EditWalletContract.Event.OnWalletDescriptionChanged -> {
                        _state.update { mState ->
                            mState.copy(walletDescription = event.description)
                        }
                    }
                    is EditWalletContract.Event.OnCreateLineClicked -> TODO()
                    is EditWalletContract.Event.OnDeleteWalletClicked -> deleteWallet(event.walletId)
                }
            }
            .launchIn(viewModelScope)

        //  getWalletInformation()

        savedStateHandle.navArgs<EditWalletNavArg>().let { args ->
            _state.update { state -> state.copy(walletId = args.walletId!!) }
        }
        if(_state.value.walletId.isNotEmpty()){
            _state.update { state -> state.copy(editWalletMode = true) }
            getWallet(_state.value.walletId)
        } else{
            _state.update { state -> state.copy(editWalletMode = false) }
        }
    }

    override fun onEvent(event: EditWalletContract.Event) {
        when (event) {
            is EditWalletContract.Event.OnSaveButtonCLicked -> {
                if (checkValues()){
                    if(_state.value.editWalletMode){
                        saveWallet()
                    } else{
                        createWallet()
                    }
                }
            }
            is EditWalletContract.Event.OnWalletTitleChanged -> {
                _state.update { mState ->
                    mState.copy(walletTitle = event.title)
                }
            }
            is EditWalletContract.Event.OnWalletBalanceChanged -> {
                _state.update { mState ->
                    mState.copy(walletBalance = event.balance)
                }
            }
            is EditWalletContract.Event.OnWalletDescriptionChanged -> {
                _state.update { mState ->
                    mState.copy(walletDescription = event.description)
                }
            }
            EditWalletContract.Event.OnBackButtonClicked,
            -> viewModelScope.launch { clickState.send(event) }
            is EditWalletContract.Event.OnCreateLineClicked -> {
                //Navigate to LineSetup
            }
            is EditWalletContract.Event.OnDeleteWalletClicked -> deleteWallet(event.walletId)
        }
    }


    private fun getWallet(walletId: String) {
        viewModelScope.launch {
            walletUseCase.getWallet.execute(walletId.toString()).let { it ->
                when (it) {
                    is Resource.Error -> effectChannel.send(
                        EditWalletContract.Effect.ShowWalletError(
                            SocialSignInError.UnknownError
                        )
                    )
                    is Resource.Success -> {
                        /*effectChannel.send(
                            EditWalletContract.Effect.ReceiveWalletSuccessful(
                                wallet = it.data
                            )
                        )*/
                        _state.update { mState ->
                            mState.copy(wallet = it.data)
                        }
                    }
                }
            }
        }
    }

    private fun saveWallet() {
        viewModelScope.launch {
            val wallet = Wallet(
                id = _state.value.walletId,
                name = _state.value.walletTitle,
                balance = _state.value.walletBalance,
                description = _state.value.walletDescription,
                lines = _state.value.walletLines
            )
            walletUseCase.editWallet.execute(wallet).let { it ->
                when (it) {
                    is Resource.Error -> effectChannel.send(
                        EditWalletContract.Effect.SaveWalletFailed(
                            it.message
                        )
                    )
                    is Resource.Success -> {
                        effectChannel.send(
                            EditWalletContract.Effect.SaveWalletSuccessful
                        )
                    }
                }
            }
        }
    }

    private fun deleteWallet(walletId:String) {
        viewModelScope.launch {
            walletUseCase.deleteWallet.execute(walletId).let { it ->
                when (it) {
                    is Resource.Error -> effectChannel.send(
                        EditWalletContract.Effect.DeleteWalletFailed(
                            it.message
                        )
                    )
                    is Resource.Success -> {
                        effectChannel.send(
                            EditWalletContract.Effect.DeleteWalletSuccessful
                        )
                    }
                }
            }
        }
    }

    private  fun checkValues():Boolean {
        if (_state.value.walletTitle.isEmpty()){
            effectChannel.trySend(
                EditWalletContract.Effect.WalletTitleIsEmpty
            )
            return false
        }
        if (_state.value.walletBalance.isEmpty()){
            effectChannel.trySend(
                EditWalletContract.Effect.WalletBalanceIsEmpty
            )
            return false
        }
        return true
    }
    private fun getTransactions() {
        viewModelScope.launch {
            //TODO
        }
    }


    private fun createWallet() {
        viewModelScope.launch {
            walletUseCase.createWallet.execute(
                name = _state.value.walletTitle,
                balance = _state.value.walletBalance,
                description = _state.value.walletDescription,
                lines = _state.value.walletLines
            ).let { it ->
                when (it) {
                    is Resource.Error -> effectChannel.send(
                        EditWalletContract.Effect.CreateWalletFailed(
                            it.data!!
                        )
                    )
                    is Resource.Success -> {
                        effectChannel.send(
                            EditWalletContract.Effect.CreateWalletSuccessful
                        )
                    }
                }
            }
        }
    }

}