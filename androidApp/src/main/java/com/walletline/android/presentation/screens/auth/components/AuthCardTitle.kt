package com.walletline.android.presentation.screens.auth.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.theme.WalletLineTheme

@Composable
fun AuthCardTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center
    )
}


@Preview
@Composable
private fun AuthCardTitlePreview() {
    WalletLineTheme {
        WalletLineBackground {
            AuthCardTitle(
                text = "Enter by socials"
            )
        }
    }
}