package com.walletline.android.presentation.screens.auth.verify_email.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.R
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding
import com.walletline.android.presentation.util.Constants

@Composable
fun VerifyEmailBodyText(
    email: String,
    onEmailClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(R.string.enter_the_4_digit_code_we_sent_to),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.customColor.neutrals.three,
            textAlign = TextAlign.Center
        )

        TextButton(
            onClick = onEmailClicked,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.customColor.neutrals.four,
                disabledContentColor = MaterialTheme.customColor.neutrals.four.copy(alpha = Constants.DisabledAlpha)
            )
        ) {
            Text(
                text = email,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.customColor.neutrals.four,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.width(MaterialTheme.padding.small))

            Icon(
                modifier = Modifier
                    .align(Alignment.Bottom),
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = stringResource(
                    R.string.desc_edit_icon
                ),
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
private fun VerifyEmailBodyTextPreview() {
    WalletLineTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.customColor.neutrals.main)
        ) {
            VerifyEmailBodyText(email = "test@test.com", onEmailClicked = {})
        }
    }
}