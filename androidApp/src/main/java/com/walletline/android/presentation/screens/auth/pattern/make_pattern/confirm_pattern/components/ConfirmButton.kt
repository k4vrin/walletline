package com.walletline.android.presentation.screens.auth.pattern.make_pattern.confirm_pattern.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.util.DevicesPreviews
import com.walletline.android.presentation.util.ThemePreviews
import com.walletline.android.presentation.util.sdp

@Composable
fun ConfirmButton(
    width: Dp,
    height: Dp,
    text: String,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.customColor.neutrals.one,
    contentColor: Color = MaterialTheme.customColor.neutrals.six,
    onClick: () -> Unit,
    enabled:Boolean
) {
    Button(
        modifier = modifier
            .width(width)
            .height(height),
        onClick = onClick,
        shape = RoundedCornerShape(Dimen.DefaultButtonCornerRadius),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = MaterialTheme.customColor.neutrals.two,
            disabledContentColor = MaterialTheme.customColor.neutrals.five
        ),
        enabled = enabled
    )
    {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(
                lineHeight = Dimen.TextLineHeight20,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }


}

@ThemePreviews
@DevicesPreviews
@Composable
fun ConfirmButtonPreview() {
    WalletLineTheme {
        WalletLineBackground {
            ConfirmButton(
                width = 165.dp,
                modifier = Modifier
                    .align(Alignment.Center),
                height = 33.sdp,
                text = "Skip",
                enabled = true,
                onClick = {
                }
            ) 
        }
    }
}