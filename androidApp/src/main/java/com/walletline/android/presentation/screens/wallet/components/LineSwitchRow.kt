package com.walletline.android.presentation.screens.wallet.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.theme.padding
import com.walletline.android.R
import com.walletline.android.presentation.theme.Dimen

@Composable
fun LineSwitchRow(modifier: Modifier = Modifier) {
    val linesEnabled = remember { mutableStateOf(true) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimen.LineSwitchButtonHeight)
            .clip(RoundedCornerShape(Dimen.DefaultButtonCornerRadius))
            .background(color = Color(0xFFEFEFEF))
            .padding(MaterialTheme.padding.small),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.padding.extraMedium)
    ) {
        LineSwitchButton(modifier = Modifier.weight(1f),onClick = {
            linesEnabled.value = true
        }, linesEnabled = linesEnabled,text = R.string.lines)

        LineSwitchButton(modifier = Modifier.weight(1f), onClick = {
            linesEnabled.value = false
        }, linesEnabled = linesEnabled, text = R.string.transactions)
    }
}

@Preview
@Composable
fun LineSwitchButtonPreview() {
    WalletLineBackground {
        LineSwitchRow()
    }
}