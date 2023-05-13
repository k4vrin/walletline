package com.walletline.android.presentation.screens.auth.verify_email.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.walletline.android.R
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.util.underlineText

@Composable
fun ResendCodeSection(
    modifier: Modifier = Modifier,
    onResendClick: () -> Unit,
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(R.string.didntReceiveCode),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.customColor.neutrals.three,
            textAlign = TextAlign.Center
        )

        TextButton(onClick = onResendClick) {
            Text(
                text = underlineText(
                    text = stringResource(R.string.resendCode),
                    color = MaterialTheme.customColor.main.four
                ),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
            )
        }
    }
}
