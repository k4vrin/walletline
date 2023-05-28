package com.walletline.android.presentation.screens.auth.pattern.component

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.screens.components.WalletLineButton
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.util.DevicesPreviews
import com.walletline.android.presentation.util.ThemePreviews

@Composable
fun PatternButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.customColor.neutrals.main,
        contentColor = MaterialTheme.customColor.neutrals.six,
        disabledContainerColor = MaterialTheme.customColor.neutrals.two,
        disabledContentColor = MaterialTheme.customColor.neutrals.five,
    ),
    borderColor: Color = MaterialTheme.customColor.neutrals.main,
    disabledBorderColor: Color = MaterialTheme.customColor.neutrals.two,
    onClick: () -> Unit,
) {
    WalletLineButton(
        modifier = modifier,
        text = text,
        onClick = onClick,
        isLoading = isLoading,
        enabled = enabled,
        colors = colors,
        borderColor = borderColor,
        disableBorderColor = disabledBorderColor
    )
}

@ThemePreviews
@DevicesPreviews
@Composable
fun PatternContinueButtonPreview() {
    WalletLineTheme {
        WalletLineBackground {
            PatternButton(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "Skip", enabled = true, onClick = {}
            )
        }
    }
}