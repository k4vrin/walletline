package com.walletline.android.presentation.screens.wallet.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.walletline.android.R
import com.walletline.android.presentation.theme.customColor

@Composable
fun WalletAccountDetailTitle(){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.account_balance),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.customColor.neutrals.two
        )
        Text(
            text = stringResource(id = R.string.current_lines),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.customColor.neutrals.two
        )
    }
}