package com.walletline.android.presentation.screens.auth.pattern.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.walletline.android.presentation.theme.customColor


@Composable
fun PatternRememberText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.customColor.neutrals.two,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

