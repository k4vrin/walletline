package com.walletline.android.presentation.screens.auth.pattern.make_pattern.confirm_pattern.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.util.Constants
import com.walletline.android.presentation.util.DevicesPreviews
import com.walletline.android.presentation.util.ThemePreviews
import com.walletline.android.presentation.util.sdp

@Composable
fun SkipButton(
    width: Dp,
    height: Dp,
    text: String,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.customColor.neutrals.main,
    contentColor: Color = MaterialTheme.customColor.neutrals.main,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .widthIn(min = width)
            .height(height),
        onClick = onClick,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor.copy(alpha = Constants.DisabledButtonAlpha),
            contentColor = contentColor,
            disabledContainerColor = containerColor.copy(alpha = Constants.DisabledButtonAlpha),
            disabledContentColor = contentColor
        )
    )
    {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }


}

@ThemePreviews
@DevicesPreviews
@Composable
fun OnBoardingButtonPreview() {
    WalletLineTheme {
        WalletLineBackground {
            SkipButton(
                modifier = Modifier
                    .align(Alignment.Center),
                width = 58.dp,
                height = 33.sdp,
                text = "Skip"
            ) {

            }
        }
    }
}