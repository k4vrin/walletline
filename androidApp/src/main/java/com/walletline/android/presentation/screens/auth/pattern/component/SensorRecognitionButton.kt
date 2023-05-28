package com.walletline.android.presentation.screens.auth.pattern.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.R
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding

@Composable
fun SensorRecognitionButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {

    Surface(
        onClick = onClick,
        modifier = modifier.height(Dimen.DefaultButtonHeight),
        enabled = enabled,
        shape = RoundedCornerShape(Dimen.DefaultButtonCornerRadius),
        color = Color.Transparent,
        contentColor = MaterialTheme.customColor.neutrals.two,
        border = BorderStroke(
            width = Dimen.DefaultButtonStrokeWidth,
            color = MaterialTheme.customColor.neutrals.two,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .height(Dimen.DefaultButtonHeight)
                .padding(
                    horizontal = MaterialTheme.padding.medium,
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(Dimen.SensorRecognitionImageSize),
                painter = painterResource(id = R.drawable.union),
                contentDescription = stringResource(R.string.desc_finger),
                colorFilter = ColorFilter.tint(MaterialTheme.customColor.neutrals.two)
            )

            Spacer(modifier = Modifier.width(MaterialTheme.padding.smallMedium))

            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.useMyFinger),
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.customColor.neutrals.two,
            )
        }
    }

}

@Preview
@Composable
private fun SensorRecognitionButtonPreview() {
    WalletLineTheme {
        WalletLineBackground {
            SensorRecognitionButton(onClick = {})
        }
    }
}