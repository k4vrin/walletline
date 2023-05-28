package com.walletline.android.presentation.screens.auth.components

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.screens.components.WalletLineButton
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.util.DevicesPreviews
import com.walletline.android.presentation.util.ThemePreviews

@Composable
fun AuthButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    onClick: () -> Unit,
) {
    WalletLineButton(
        text = text,
        modifier = modifier,
        enabled = enabled,
        isLoading = isLoading,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.customColor.main.four,
            contentColor = MaterialTheme.customColor.neutrals.main,
            disabledContainerColor = MaterialTheme.customColor.main.two,
            disabledContentColor = MaterialTheme.customColor.neutrals.main
        ),
        borderColor = MaterialTheme.customColor.main.four,
        disableBorderColor = MaterialTheme.customColor.main.two,
    )
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
                text = "Send Code"
            ) {

            }
        }
    }
}