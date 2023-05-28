package com.walletline.android.presentation.screens.auth.pattern.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.util.ssp


@Composable
fun PatternTitleText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.headlineSmall.copy(lineHeight = 24.ssp),
        color = MaterialTheme.customColor.neutrals.one,
        textAlign = TextAlign.Center,
    )
}

