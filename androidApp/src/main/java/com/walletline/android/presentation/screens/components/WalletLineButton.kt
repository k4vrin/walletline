package com.walletline.android.presentation.screens.components

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.walletline.android.presentation.screens.auth.components.FadingDotLoading
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.util.Constants

@Composable
fun WalletLineButton(
    text: String,
    modifier: Modifier = Modifier,
    height: Dp = Dimen.DefaultButtonHeight,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.customColor.main.four,
        contentColor = MaterialTheme.customColor.neutrals.main,
        disabledContainerColor = MaterialTheme.customColor.main.four.copy(alpha = Constants.DisabledAlpha),
        disabledContentColor = MaterialTheme.customColor.neutrals.main.copy(alpha = Constants.DisabledAlpha)
    ),
    borderColor: Color = MaterialTheme.customColor.neutrals.two,
    disableBorderColor: Color = MaterialTheme.customColor.neutrals.two,
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
            color = if (enabled) borderColor else disableBorderColor
        ),
        colors = colors
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