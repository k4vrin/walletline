package com.walletline.android.presentation.screens.auth.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.Padding
import com.walletline.android.presentation.theme.WalletLineTheme

@Composable
fun AuthCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(Dimen.DefaultCardCornerRadius),
        shadowElevation = Dimen.DefaultCardElevation,
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            content = content
        )
    }
}

@Preview
@Composable
private fun AuthCardPreview() {
    WalletLineTheme {
        WalletLineBackground {
            AuthCard(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(Padding.medium)
            ) {

            }
        }
    }
}