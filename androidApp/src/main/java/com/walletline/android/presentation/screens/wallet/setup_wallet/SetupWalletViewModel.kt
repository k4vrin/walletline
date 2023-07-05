package com.walletline.android.presentation.screens.wallet.setup_wallet


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walletline.domain.model.auth.SocialSignInError
import com.walletline.domain.use_case.wallet.WalletUseCase
import com.walletline.domain.util.Resource
import com.walletline.presentation.screens.wallet.SetupWalletState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SetupWalletViewModel(
    private val walletUseCase: WalletUseCase, private val walletId: String
) : ViewModel(), SetupWalletContract {

    private val _state = MutableStateFlow(SetupWalletState())

    override val state: StateFlow<SetupWalletState> = _state.asStateFlow()

    private val effectChannel = Channel<SetupWalletContract.Effect>()

    override val effect: Flow<SetupWalletContract.Effect> = effectChannel.receiveAsFlow()

    private val clickState = Channel<SetupWalletContract.Event>()

    init {
        clickState
            .receiveAsFlow()
            .debounce(400)
            .onEach { event ->
                when (event) {
                    is SetupWalletContract.Event.OnSaveButtonCLicked -> Unit

                    SetupWalletContract.Event.OnBackButtonClicked -> {
                        effectChannel.trySend(SetupWalletContract.Effect.NavigateBack)
                    }
                    is SetupWalletContract.Event.OnWalletTitleChanged -> {
                        _state.update { mState ->
                            mState.copy(walletTitle = event.title)
                        }
                    }
                    is SetupWalletContract.Event.OnWalletBalanceChanged -> {
                        _state.update { mState ->
                            mState.copy(walletBalance = event.balance)
                        }
                    }
                    is SetupWalletContract.Event.OnWalletDescriptionChanged -> {
                        _state.update { mState ->
                            mState.copy(walletDescription = event.description)
                        }
                    }
                    is SetupWalletContract.Event.OnCreateLineClicked -> {
                        effectChannel.trySend(SetupWalletContract.Effect.NavigateToSetupLine)
                    }
                }
            }
            .launchIn(viewModelScope)

    }


    override fun onEvent(event: SetupWalletContract.Event) {
        when (event) {
            is SetupWalletContract.Event.OnSaveButtonCLicked -> {
                if (checkValues()){
                    createWallet()
                }
            }
            is SetupWalletContract.Event.OnWalletTitleChanged -> {
                _state.update { mState ->
                    mState.copy(walletTitle = event.title)
                }
            }
            is SetupWalletContract.Event.OnWalletBalanceChanged -> {
                _state.update { mState ->
                    mState.copy(walletBalance = event.balance)
                }
            }
            is SetupWalletContract.Event.OnWalletDescriptionChanged -> {
                _state.update { mState ->
                    mState.copy(walletDescription = event.description)
                }
            }
            SetupWalletContract.Event.OnBackButtonClicked,
            -> viewModelScope.launch { clickState.send(event) }
            is SetupWalletContract.Event.OnCreateLineClicked -> TODO()
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
                        SetupWalletContract.Effect.CreateWalletFailed(
                            it.data!!
                        )
                    )
                    is Resource.Success -> {
                        effectChannel.send(
                            SetupWalletContract.Effect.CreateWalletSuccessful
                        )
                    }
                }
            }
        }
    }

    private  fun checkValues():Boolean {
        if (_state.value.walletTitle.isEmpty()){
            effectChannel.trySend(
                SetupWalletContract.Effect.WalletTitleIsEmpty
            )
            return false
        }
        if (_state.value.walletBalance.isEmpty()){
            effectChannel.trySend(
                SetupWalletContract.Effect.WalletBalanceIsEmpty
            )
            return false
        }
        return true
    }

}