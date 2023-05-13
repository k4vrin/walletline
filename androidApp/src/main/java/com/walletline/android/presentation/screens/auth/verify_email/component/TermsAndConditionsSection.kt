package com.walletline.android.presentation.screens.auth.verify_email.component

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.R
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.util.appendSpace

private const val TermsTag: String = "terms"
private const val PolicyTag: String = "policy"
@Composable
fun TermsAndConditionsSection(
    modifier: Modifier = Modifier,
    onTermsClicked: () -> Unit,
    onPolicyClick: () -> Unit
) {

    val annotatedString = partiallyHyperlinkText(
        firstNormal = stringResource(id = R.string.term1),
        secondNormal = stringResource(id = R.string.term3),
        firstHyperlinkSection = stringResource(id = R.string.term2),
        secondHyperlinkSection = stringResource(R.string.term4),
        normalSectionColor = MaterialTheme.customColor.neutrals.two,
        hyperlinkColor = MaterialTheme.customColor.neutrals.two,
    )

    ClickableText(
        modifier = modifier,
        text = annotatedString,
        style = MaterialTheme.typography.bodySmall.copy(textAlign = TextAlign.Center),
        onClick = { offset ->
            annotatedString.getStringAnnotations(
                tag = TermsTag,
                start = offset,
                end = offset
            ).takeIf {
                it.isNotEmpty()
            }?.let {
                onTermsClicked()
            }

            annotatedString.getStringAnnotations(
                tag = PolicyTag,
                start = offset,
                end = offset
            ).takeIf {
                it.isNotEmpty()
            }?.let {
                onPolicyClick()
            }
        }
    )
}

private fun partiallyHyperlinkText(
    firstNormal: String,
    secondNormal: String,
    firstHyperlinkSection: String,
    secondHyperlinkSection: String,
    normalSectionColor: Color,
    hyperlinkColor: Color
): AnnotatedString = buildAnnotatedString {

    withStyle(
        style = SpanStyle(
            color = normalSectionColor,
            fontWeight = FontWeight.Medium
        )
    ) {
        append(firstNormal)
    }

    appendSpace()

    pushStringAnnotation(
        tag = TermsTag,
        annotation = TermsTag
    )

    withStyle(
        style = SpanStyle(
            color = hyperlinkColor,
            textDecoration = TextDecoration.Underline,
            fontWeight = FontWeight.Bold
        )
    ) {
        append(firstHyperlinkSection)
    }
    pop()

    appendSpace()

    withStyle(
        style = SpanStyle(
            color = normalSectionColor,
            fontWeight = FontWeight.Medium
        )
    ) {
        append(secondNormal)
    }

    appendSpace()

    pushStringAnnotation(
        tag = PolicyTag,
        annotation = PolicyTag
    )
    withStyle(
        style = SpanStyle(
            color = hyperlinkColor,
            textDecoration = TextDecoration.Underline,
            fontWeight = FontWeight.Bold
        )
    ) {
        append(secondHyperlinkSection)
    }
    pop()
}

@Preview
@Composable
fun TermsPreview() {
    WalletLineTheme {
        WalletLineBackground {
            TermsAndConditionsSection(
                onPolicyClick = {},
                onTermsClicked = {}
            )
        }
    }
}