package com.walletline.android.presentation.screens.wallet.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.walletline.android.presentation.theme.customColor
import com.walletline.presentation.screens.wallet.WalletState

@Composable
fun WalletAccountDetailContent(state : WalletState, modifier:Modifier = Modifier){
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
    ) {
        Text(
            state.balanceValueCurrency,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.customColor.neutrals.main
        )
        Text(
            state.accountBalanceCorrectValue,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.customColor.neutrals.main
        )
        Text(
            ".${state.accountBalanceCorrectValue}",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.customColor.neutrals.two
        )
        Spacer(modifier = Modifier.weight(1f))

        Text(
            state.currentLines,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.customColor.neutrals.main
        )
    }
}