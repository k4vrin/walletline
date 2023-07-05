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
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.walletline.android.R
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.util.DevicesPreviews
import com.walletline.android.presentation.util.ThemePreviews

@Composable
fun SaveButton(
    text : Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimen.DefaultButtonHeight),
        onClick = onClick,
        shape = RoundedCornerShape(Dimen.DefaultButtonCornerRadius),
        contentPadding = PaddingValues(Dp.Hairline),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.customColor.main.four,
            contentColor = MaterialTheme.customColor.neutrals.main,
//            disabledContainerColor = MaterialTheme.customColor.neutrals.main.copy(alpha = 0.15f),
//            disabledContentColor = MaterialTheme.customColor.neutrals.main
        )
    )
    {
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.labelLarge.copy(lineHeight = Dimen.TextLineHeight17),
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
            SaveButton(
                text = R.string.save,
                modifier = Modifier
                    .align(Alignment.Center),
            ) {

            }
        }
    }
}