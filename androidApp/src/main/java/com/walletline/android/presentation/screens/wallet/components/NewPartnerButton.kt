package com.walletline.android.presentation.screens.wallet.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.util.DevicesPreviews
import com.walletline.android.presentation.util.ThemePreviews
import com.walletline.android.R

@Composable
fun NewPartnerButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .dashedBorder(
                Dimen.ButtonStrokeWidth,
                MaterialTheme.customColor.neutrals.three,
                Dimen.DefaultButtonHeight
            )
            .size(Dimen.DefaultButtonHeight),
        onClick = onClick,
        shape = CircleShape,
        contentPadding = PaddingValues(Dp.Hairline),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.customColor.neutrals.three,

        )
    )
    {
        Icon(
            painter = painterResource(id = R.drawable.ic_add),
            contentDescription = null,
            modifier = Modifier.size(Dimen.DefaultWalletButtonSize),
            tint = MaterialTheme.customColor.neutrals.three
        )
    }
}


@ThemePreviews
@DevicesPreviews
@Composable
private fun CreateLineButtonPreview() {
    WalletLineTheme {
        WalletLineBackground {
            DashedBorderedButton(
                text = R.string.create_line,
                modifier = Modifier
                    .align(Alignment.Center),
            ) {

            }
        }
    }
}