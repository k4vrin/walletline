package com.walletline.android.presentation.screens.onboarding.components

import androidx.compose.foundation.layout.PaddingValues
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
fun SkipButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .width(Dimen.SkipButtonWidth)
            .height(Dimen.SkipButtonHeight),
        onClick = onClick,
        shape = RoundedCornerShape(50),
        contentPadding = PaddingValues(Dp.Hairline),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.customColor.neutrals.main.copy(alpha = 0.15f),
            contentColor = MaterialTheme.customColor.neutrals.main,
            disabledContainerColor = MaterialTheme.customColor.neutrals.main.copy(alpha = 0.15f),
            disabledContentColor = MaterialTheme.customColor.neutrals.main
        )
    )
    {
        Text(
            text = stringResource(R.string.skip),
            style = MaterialTheme.typography.bodySmall,
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
            ) {

            }
        }
    }
}