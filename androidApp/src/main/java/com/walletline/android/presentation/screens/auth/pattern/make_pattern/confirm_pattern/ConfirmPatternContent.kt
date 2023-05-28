package com.walletline.android.presentation.screens.auth.pattern.make_pattern.confirm_pattern

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.R
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.screens.auth.pattern.component.PatternRememberText
import com.walletline.android.presentation.screens.auth.pattern.component.PatternTitleText
import com.walletline.android.presentation.screens.auth.pattern.component.pattern_model.PasswordPattern
import com.walletline.android.presentation.screens.auth.pattern.make_pattern.MakePatternContract
import com.walletline.android.presentation.screens.auth.pattern.make_pattern.confirm_pattern.components.RetryConfirmButtons
import com.walletline.android.presentation.screens.auth.pattern.make_pattern.confirm_pattern.components.ConfirmPatternHeader
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.padding
import com.walletline.presentation.screens.auth.pattern.make_pattern.MakePatternState

@Composable
fun ConfirmPatternContent(
    state: MakePatternState,
    onEvent: (MakePatternContract.Event) -> Unit,
) {
    WalletLineBackground(
        isScrollEnabled = state.isScrollEnabled
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Spacer(modifier = Modifier.height(MaterialTheme.padding.large))

            ConfirmPatternHeader(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.padding.medium),
                onSkipButtonClicked = {
                    onEvent(MakePatternContract.Event.SkipPatternClicked)
                }
            )

            Spacer(modifier = Modifier.height(MaterialTheme.padding.large))

            PatternRememberText(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.padding.medium),
                text = stringResource(R.string.rememberPattern)
            )

            Spacer(modifier = Modifier.height(MaterialTheme.padding.extraLarge))

            PatternTitleText(
                text = stringResource(R.string.confirmYourPattern),
            )

            Spacer(modifier = Modifier.height(MaterialTheme.padding.large * 2))

            PasswordPattern(
                onStart = {
                    onEvent(
                        MakePatternContract.Event.ConfirmPatternChange(
                            pattern = "",
                            isScrollEnabled = false
                        )
                    )
                },
                onResult = {
                    onEvent(
                        MakePatternContract.Event.ConfirmPatternChange(
                            pattern = it,
                            isScrollEnabled = true
                        )
                    )
                }
            )

            Spacer(modifier = Modifier.height(MaterialTheme.padding.large * 2))

            RetryConfirmButtons(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.padding.medium),
                onRetryButtonClick = {
                    onEvent(MakePatternContract.Event.RetryPatternClicked)
                },
                onConfirmButtonClicked = {
                    onEvent(MakePatternContract.Event.ConfirmPatternClicked)
                },
                isConfirmButtonEnabled = state.isConfirmButtonEnable
            )
        }

    }
}

@Preview
@Composable
fun CreatePatternPreview() {
    WalletLineTheme {
        ConfirmPatternContent(
            state = MakePatternState(),
            onEvent = {}
        )
    }
}