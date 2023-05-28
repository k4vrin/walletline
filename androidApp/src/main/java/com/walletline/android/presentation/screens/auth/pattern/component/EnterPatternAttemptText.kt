package com.walletline.android.presentation.screens.auth.pattern.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.walletline.android.presentation.util.appendSpace

@Composable
fun EnterPatternAttemptText(
    attempts: Int
) {
    val annotatedString = buildAnnotatedString {
        append("if you enter the pattern wrongly for")
        appendSpace()
        withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
            append(attempts.toString())
        }
        appendSpace()
        append("times, the app will be lock!")
    }

    Text(
        text = annotatedString,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center
    )
}