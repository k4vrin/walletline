package com.walletline.android.presentation.screens.auth.pattern.component.pattern_model

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.customColor


@Composable
fun PasswordPattern(
    onStart: (dot: Dot) -> Unit,
    onResult: (result: String) -> Unit,
) {
    Box(
        modifier = Modifier
            .size(Dimen.PasswordPatternSize),
        contentAlignment = Alignment.Center
    ) {
        ComposeLockMain(
            modifier = Modifier
                .fillMaxSize(),
            dotsInRow = 3,
            sensitivity = 80f,
            dotsDefaultColor = MaterialTheme.customColor.neutrals.main,
            dotsSecondColor = MaterialTheme.customColor.neutrals.main,
            dotSize = 12f,
            linesColor = MaterialTheme.customColor.neutrals.main,
            linesStroke = 3f,
            animationDuration = 200,
            animationDelay = 100,
            onStart = onStart,
            onResult = { _, pass ->
                onResult(pass)
            },
            onDotConnected = {}
        )

    }
}

@Preview
@Composable
fun PasswordPatternPreview() {
    WalletLineBackground() {
        PasswordPattern(
            onStart = {
            },
            onResult = {

            }
        )

    }
}