package com.walletline.android.presentation.screens.wallet.edit_wallet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.util.DevicesPreviews
import com.walletline.android.presentation.util.ThemePreviews
import com.walletline.android.R
import com.walletline.android.presentation.theme.padding

@Composable
fun ShareButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimen.ShareButtonHeight),
        onClick = onClick,
        shape = RoundedCornerShape(Dimen.ShareButtonCornerReadius),
        contentPadding = PaddingValues(Dp.Hairline),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.customColor.main.one,
            contentColor = MaterialTheme.customColor.main.four,
        )
    )
    {
        Row(
            modifier = Modifier.padding(
                vertical = MaterialTheme.padding.small,
                horizontal = MaterialTheme.padding.smallMedium
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_share),
                contentDescription = null,
                tint = MaterialTheme.customColor.main.four
            )
            Text(
                text = stringResource(R.string.share_with_contacts),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.customColor.main.four,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = MaterialTheme.padding.small)
            )
        }
    }
}


@ThemePreviews
@DevicesPreviews
@Composable
private fun ShareButtonPreview() {
    WalletLineTheme {
        WalletLineBackground {
            ShareButton(
                modifier = Modifier
                    .align(Alignment.Center),
            ) {

            }
        }
    }
}
