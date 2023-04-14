package com.walletline.android.presentation.screens.onboarding.components


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.util.Constants
import com.walletline.android.presentation.util.DevicesPreviews
import com.walletline.android.presentation.util.ThemePreviews
import com.walletline.android.presentation.util.sdp

@Composable
fun OnBoardingCardButton(
    height: Dp,
    text: String,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .border(1.dp, color = MaterialTheme.colorScheme.scrim.copy(alpha = 0.1f),shape = RoundedCornerShape(Dimen.ButtonsCornerRadius),
            ),

        onClick = onClick,
        shape = RoundedCornerShape(Dimen.ButtonsCornerRadius),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
//            disabledContainerColor = containerColor.copy(alpha = Constants.DisabledButtonAlpha),
//            disabledContentColor = contentColor.copy(alpha = Constants.DisabledAlpha)
//
        )
    )
    {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center,
        )
    }
}

@ThemePreviews
@DevicesPreviews
@Composable
fun OnBoardingCardButtonPreview() {
    WalletLineTheme {
        WalletLineBackground {
            OnBoardingCardButton(
                modifier = Modifier
                    .align(Alignment.Center),
                height = 48.sdp,
                text = "Next"
            ) {

            }
        }
    }
}