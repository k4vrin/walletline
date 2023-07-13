package com.walletline.android.presentation.screens.wallet.components.lines

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding

@Composable
fun ValueText(value: String, value2: String) {
    Text(
        value,
        style = MaterialTheme.typography.displaySmall.copy(
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Normal
        ),
        textAlign = TextAlign.Center,
        color = MaterialTheme.customColor.neutrals.six,
    )
    Text(
        ".$value2", style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center,
        color = MaterialTheme.customColor.neutrals.six,
        modifier = Modifier.padding(end = MaterialTheme.padding.medium)
    )
}