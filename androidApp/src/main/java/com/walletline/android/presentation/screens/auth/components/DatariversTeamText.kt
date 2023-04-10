package com.walletline.android.presentation.screens.auth.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.R
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.util.appendSpace

@Composable
fun DatariversTeamText(
    modifier: Modifier = Modifier
) {
    val text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Normal
            )
        ) {
            append(stringResource(R.string.designed_by))
        }

        appendSpace()

        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold
            )
        ) {
            append(stringResource(R.string.dr_org))
        }

        appendSpace()

        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Normal
            )
        ) {
            append(stringResource(R.string.dev_teams))
        }
    }

    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.4f)
    )
}

@Preview(showBackground = true)
@Composable
private fun DatariversTeamTextPreview() {
    WalletLineTheme {
        DatariversTeamText()
    }
}