package com.walletline.android.presentation.screens.wallet.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.walletline.android.R
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.util.DevicesPreviews
import com.walletline.android.presentation.util.ThemePreviews

@Composable
fun DashedBorderedButton(
    text:Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,

    ) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .dashedBorder(Dimen.ButtonStrokeWidth, MaterialTheme.customColor.neutrals.three, Dimen.DefaultButtonCornerRadius)
            .height(Dimen.DefaultButtonHeight),
        onClick = onClick,
        shape = RoundedCornerShape(Dimen.DefaultButtonCornerRadius),
        contentPadding = PaddingValues(Dp.Hairline),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.customColor.neutrals.three,
//            disabledContainerColor = MaterialTheme.customColor.neutrals.main.copy(alpha = 0.15f),
//            disabledContentColor = MaterialTheme.customColor.neutrals.main
        )
    )
    {
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterVertically)

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