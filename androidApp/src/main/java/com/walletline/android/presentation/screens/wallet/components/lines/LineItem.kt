package com.walletline.android.presentation.screens.wallet.components.lines

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.walletline.android.R
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding

@Composable

fun LineItem(modifier: Modifier = Modifier) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(color = MaterialTheme.customColor.neutrals.main)
            .padding(
                vertical = MaterialTheme.padding.extraMedium,
                horizontal = MaterialTheme.padding.medium
            )


    ) {
        Box(
            modifier = Modifier
                .size(Dimen.WalletIconBackSize)
                .clip(RoundedCornerShape(Dimen.DefaultButtonCornerRadius))
                .background(color = MaterialTheme.customColor.neutrals.main)
                .border(
                    width = Dimen.DefaultButtonStrokeWidth,
                    color = MaterialTheme.customColor.neutrals.two,
                    shape = RoundedCornerShape(Dimen.DefaultButtonCornerRadius)
                )
        )
        Column(
            modifier = Modifier.padding(start = MaterialTheme.padding.smallMedium)
        ) {
            Text(
                text = stringResource(id = R.string.needs),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.customColor.neutrals.six,
            )
            Text(
                "50%", style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.customColor.neutrals.three,
                modifier = Modifier.padding(top = MaterialTheme.padding.small)
            )
        }
        Spacer(Modifier.weight(1f))
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                "$",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.customColor.neutrals.six,
                modifier = Modifier.padding(
                    end = MaterialTheme.padding.extraSmall,
                    bottom = 2.dp
                )

            )
            ValueText(value = "560", value2 = "00")
        }
        IconButton(
            onClick = {},
            modifier = Modifier
                .size(Dimen.WalletLineBackIconSize)
                .clip(CircleShape)
                .background(color = MaterialTheme.customColor.neutrals.main)
                .border(
                    width = Dimen.DefaultButtonStrokeWidth,
                    color = MaterialTheme.customColor.neutrals.two,
                    shape = CircleShape
                )

        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_right), contentDescription = null,
                tint = Color.Unspecified,

                )
        }
    }
}

@Preview
@Composable
private fun LineItemPreview() {
    WalletLineBackground {
        LineItem()
    }
}