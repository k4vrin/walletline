package com.walletline.android.presentation.screens.auth.pattern.make_pattern.confirm_pattern.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.walletline.android.R
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.customColor

@Composable
fun ConfirmPatternHeader(
    modifier: Modifier = Modifier,
    onSkipButtonClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.set_pattern),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.customColor.neutrals.one,
        )

        SkipButton(
            width = Dimen.SkipButtonWidth,
            height = Dimen.SkipButtonHeight,
            text = stringResource(id = R.string.skip),
            onClick = onSkipButtonClicked
        )

    }
}