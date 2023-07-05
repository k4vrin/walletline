package com.walletline.android.presentation.screens.wallet


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walletline.domain.model.LineData
import com.walletline.domain.model.PartnerData
import com.walletline.domain.model.wallet.Wallet
import com.walletline.domain.use_case.wallet.WalletUseCase
import com.walletline.presentation.screens.wallet.WalletState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class WalletViewModel(private val walletUseCase: WalletUseCase) : ViewModel(), WalletContract {

    private val _state = MutableStateFlow(WalletState())

    override val state: StateFlow<WalletState> = _state.asStateFlow()

    private val effectChannel = Channel<WalletContract.Effect>()

    override val effect: Flow<WalletContract.Effect> = effectChannel.receiveAsFlow()

    private val clickState = Channel<WalletContract.Event>()

    init {
        clickState
            .receiveAsFlow()
            .debounce(400)
            .onEach { event ->
                when (event) {
                    is WalletContract.Event.OnLineDetailClicked -> {
                        getLineDetail(event.lineId, event.walletId)
                    }

                    is WalletContract.Event.OnCreateLineClicked -> {

                    }

                    is WalletContract.Event.OnLinesClicked -> {
                        getLines(event.walletId)
                    }

                    is WalletContract.Event.OnWalletChanged -> {
                        getWalletInformation(event.walletId)
                    }

                    is WalletContract.Event.OnEditWalletClicked -> {
                        effectChannel.trySend(WalletContract.Effect.NavigateToEditWalletScreen(event.walletId))
                    }

                    is WalletContract.Event.OnViewAllPartnersClicked -> {
                        effectChannel.trySend(WalletContract.Effect.ShowPartnersList)
                    }

                    WalletContract.Event.OnTransactionsClicked -> getTransactions()

                    WalletContract.Event.OnBackButtonClicked -> {
                        effectChannel.trySend(WalletContract.Effect.NavigateToHomeScreen)
                    }
                    WalletContract.Event.OnCreateWalletClicked -> {
                        effectChannel.trySend(WalletContract.Effect.NavigateToSetupWalletScreen)

                    }
                }
            }
            .launchIn(viewModelScope)

        getWalletInformation("0")
    }


    override fun onEvent(event: WalletContract.Event) {
        when (event) {
            is WalletContract.Event.OnLineDetailClicked -> {
                getLineDetail(event.lineId, event.walletId)
            }
            is WalletContract.Event.OnCreateLineClicked -> {
                effectChannel.trySend(WalletContract.Effect.NavigateToLineSetupScreen)

            }
            is WalletContract.Event.OnLinesClicked -> {
                getLines(event.walletId)
            }
            is WalletContract.Event.OnWalletChanged -> {
                getWalletInformation(event.walletId)
            }
            is WalletContract.Event.OnViewAllPartnersClicked -> {
                effectChannel.trySend(WalletContract.Effect.ShowPartnersList)
            }
            is WalletContract.Event.OnEditWalletClicked -> {
                effectChannel.trySend(WalletContract.Effect.NavigateToEditWalletScreen(event.walletId))
            }

            WalletContract.Event.OnTransactionsClicked,
            WalletContract.Event.OnBackButtonClicked,
            -> viewModelScope.launch { clickState.send(event) }
            WalletContract.Event.OnCreateWalletClicked -> {
                effectChannel.trySend(WalletContract.Effect.NavigateToSetupWalletScreen)

            }
        }
    }


    private fun getWalletInformation(walletId: String) {

        getWallets()
        val lines =
            listOf(
                listOf(
                    LineData(1, 20),
                    LineData(2, 20),
                    LineData(3, 20),
                    LineData(4, 20),
                    LineData(5, 20),
                    LineData(6, 20),

                    ),
                listOf(
                    LineData(1, 20),
                    LineData(2, 30),
                ),
                listOf(
                    LineData(1, 20),
                    LineData(2, 30),
                    LineData(3, 50),
                )
            )

        val partnerItems = listOf(
            PartnerData(1, "", ""),
            PartnerData(2, "", ""),
            PartnerData(3, "", ""),
            PartnerData(4, "", ""),
        )

        checkEmptyLines()
        _state.update { it.copy(linesList = lines[walletId.toInt()]) }
        _state.update { it.copy(partnersList = partnerItems) }

        viewModelScope.launch {
            //TODO
        }
        //  getAccountDetailContent()
        getCurrentLines()
    }

    private fun getWallets() {
        viewModelScope.launch {
            walletUseCase.getWallets.execute().let { it ->
                it.collect { wallets ->
                    _state.update { mState ->
                        mState.copy(walletsList = wallets)
                    }
                    if (wallets.isNotEmpty()) {
                        setCurrentWallet(wallets[0])
                    }
                }
            }
        }
    }



    private fun setCurrentWallet(wallet: Wallet) {
        _state.update { mState ->
            mState.copy(currentWallet = wallet)
        }
    }

    private fun getTransactions() {
        viewModelScope.launch {
            //TODO
        }
    }

    private fun getAccountDetailContent() {
        viewModelScope.launch {
            //TODO
        }
        var accountBalance = _state.value.accountBalanceValue.split(".")
        _state.update { it.copy(accountBalanceCorrectValue = accountBalance[0]) }
        _state.update { it.copy(accountBalanceDecimalValue = accountBalance[1]) }
    }

    private fun getCurrentLines() {
        viewModelScope.launch {
            //TODO
        }
        var currentLines = ""
        for ((index, line) in _state.value.linesList.withIndex()) {
            currentLines += "${line.lineValue}"
            if (index != _state.value.linesList.size - 1) {
                currentLines += "|"
            }
        }
        _state.update { it.copy(currentLines = currentLines) }
    }

    private fun checkEmptyLines() {
        if (_state.value.walletId == null) {
            _state.update { it.copy(showEmptyWallet = true) }
        } else {
            _state.update { it.copy(showEmptyWallet = false) }
        }

    }

    private fun getLineDetail(lineId: Int, walletId: String) {
        viewModelScope.launch {
            //TODO
            effectChannel.send(WalletContract.Effect.NavigateToLineDetailScreen)
        }
    }

    private fun getLines(walletId: String) {
        viewModelScope.launch {
            //TODO
            effectChannel.send(WalletContract.Effect.ShowLines)
        }
    }


}