package com.walletline.android.presentation.screens.auth.components


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.R
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.padding

@Composable
fun AuthCardArrowText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
        )

        Spacer(modifier = Modifier.width(MaterialTheme.padding.small))
        
        Icon(
            modifier = Modifier
                .size(Dimen.ArrowIconSize),
            painter = painterResource(id = R.drawable.arrow_right),
            contentDescription = stringResource(R.string.desc_arrow_icon),
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
        )
    }
}

@Preview
@Composable
private fun AuthCardArrowTextPreview() {
    WalletLineTheme {
        WalletLineBackground {
            AuthCard {
                AuthCardArrowText(
                    text = "Enter by email address"
                )
            }
        }
    }
}