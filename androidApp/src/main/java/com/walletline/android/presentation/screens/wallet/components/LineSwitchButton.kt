package com.walletline.android.presentation.screens.wallet.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.R

@Composable
fun LineSwitchButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    linesEnabled: MutableState<Boolean>,
    text: Int
) {
    if ((linesEnabled.value && text == R.string.lines) || (!linesEnabled.value && text == R.string.transactions)) {
        modifier.shadow(
            elevation = 4.dp,
            spotColor = Color(0xFFCACADA),
            ambientColor = Color(0xFFCACADA),
            shape = RoundedCornerShape(Dimen.WalletButtonCornerRadius)
        )

    }
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(Dimen.WalletButtonCornerRadius),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if ((linesEnabled.value && text == R.string.lines) || (!linesEnabled.value && text == R.string.transactions)) {
                MaterialTheme.customColor.neutrals.main
            } else {
                (Color.Transparent)
            },
            contentColor = if ((linesEnabled.value && text == R.string.lines) || (!linesEnabled.value && text == R.string.transactions)) {
                MaterialTheme.customColor.neutrals.six
            } else {
                MaterialTheme.customColor.neutrals.three
            },
            disabledContainerColor = Color.Transparent,
            disabledContentColor = MaterialTheme.customColor.neutrals.three
        ),
    ) {
        Text(
            stringResource(id = text),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
        )
    }
}