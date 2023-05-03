package com.walletline.android.presentation.screens.auth.components

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.util.Constants
import com.walletline.android.presentation.util.DevicesPreviews
import com.walletline.android.presentation.util.ThemePreviews
import com.walletline.android.presentation.util.sdp

@Composable
fun AuthButton(
    text: String,
    modifier: Modifier = Modifier,
    height: Dp = Dimen.DefaultButtonHeight,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    containerColor: Color = MaterialTheme.customColor.main.four,
    contentColor: Color = MaterialTheme.customColor.neutrals.main,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(Dimen.DefaultButtonCornerRadius),
        border = BorderStroke(
            width = Dimen.DefaultButtonStrokeWidth,
            color = MaterialTheme.customColor.neutrals.two
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = containerColor.copy(alpha = Constants.DisabledAlpha),
            disabledContentColor = contentColor.copy(alpha = Constants.DisabledAlpha)
        )
    )
    {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            androidx.compose.animation.AnimatedVisibility(
                visible = isLoading,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                FadingDotLoading()
            }

            androidx.compose.animation.AnimatedVisibility(
                visible = !isLoading,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Center,
                )
            }
        }


    }


}

@ThemePreviews
@DevicesPreviews
@Composable
fun AuthButtonPreview() {
    WalletLineTheme {
        WalletLineBackground {
            AuthButton(
                modifier = Modifier
                    .align(Alignment.Center),
                height = 56.sdp,
                text = "Send Code"
            ) {

            }
        }
    }
}