package com.walletline.android.presentation.screens.auth.pattern.make_pattern.confirm_pattern.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.screens.auth.pattern.component.PatternButton
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding

@Composable
fun RetryConfirmButtons(
    onRetryButtonClick: () -> Unit,
    onConfirmButtonClicked: () -> Unit,
    isConfirmButtonEnabled: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PatternButton(
            modifier = Modifier
                .fillMaxWidth(0.5f),
            text = "Retry",
            borderColor = MaterialTheme.customColor.neutrals.main,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.customColor.neutrals.main
            ),
            onClick = onRetryButtonClick
        )

        Spacer(modifier = Modifier.width(MaterialTheme.padding.smallMedium))

        PatternButton(
            modifier = Modifier,
            text = "Confirm",
            enabled = isConfirmButtonEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.customColor.neutrals.main,
                contentColor = MaterialTheme.customColor.neutrals.five,
                disabledContainerColor = MaterialTheme.customColor.neutrals.two,
                disabledContentColor = MaterialTheme.customColor.neutrals.five,
            ),
            onClick = onConfirmButtonClicked
        )
    }
}


@Preview
@Composable
private fun RetryConfirmPreview() {
    WalletLineTheme {
        WalletLineBackground {
            RetryConfirmButtons(
                onRetryButtonClick = { /*TODO*/ },
                onConfirmButtonClicked = { /*TODO*/ },
                isConfirmButtonEnabled = true
            )
        }
    }
}