package com.walletline.android.presentation.screens.wallet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.R
import com.walletline.android.presentation.screens.wallet.components.DashedBorderedButton
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding

@Composable
fun EmptyWallet(onCreateWalletClicked:()->Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.padding.medium)
    ) {
        IconButton(
            onClick = {},
            modifier = Modifier.padding(top = MaterialTheme.padding.large)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_information), contentDescription = null,
                tint = Color.Unspecified
            )
        }
        Text(
            stringResource(R.string.no_wallet), style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.customColor.neutrals.four,
            modifier = Modifier.padding(top = MaterialTheme.padding.extraMedium)

        )
        Text(
            stringResource(R.string.manage_wallet_txt),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.customColor.neutrals.three,
            modifier = Modifier.padding(top = MaterialTheme.padding.small)

        )
        DashedBorderedButton(text = R.string.create_first_wallet,
            modifier = Modifier.padding(top = MaterialTheme.padding.smallLarge)
        ) {
            onCreateWalletClicked        }
    }
}

@Preview
@Composable
private fun EmptyLinesPreview() {
    WalletLineBackground {
       // EmptyLines()
    }
}