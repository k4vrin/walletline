package com.walletline.android.presentation.screens.auth.pattern.login_pattern.enter_pattern

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.R
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.screens.auth.pattern.component.EnterPatternAttemptText
import com.walletline.android.presentation.screens.auth.pattern.component.PatternTitleText
import com.walletline.android.presentation.screens.auth.pattern.component.pattern_model.PasswordPattern
import com.walletline.android.presentation.screens.auth.pattern.login_pattern.LoginPatternContract
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.padding
import com.walletline.domain.model.PatternValidationMessage
import com.walletline.presentation.screens.auth.pattern.login_pattern.LoginPatternState

@Composable
fun EnterPatternContent(
    state: LoginPatternState,
    onEvent: (LoginPatternContract.Event) -> Unit,
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
            Spacer(modifier = Modifier.height(MaterialTheme.padding.large * 2))

            Image(painterResource(id = R.drawable.lock), contentDescription = null)

            Spacer(modifier = Modifier.height(MaterialTheme.padding.large * 2))

            PatternTitleText(
                text = when (state.patternError) {
                    null -> stringResource(R.string.draw_unlock_pattern)
                    is PatternValidationMessage.Dynamic -> (state.patternError as PatternValidationMessage.Dynamic).message
                    PatternValidationMessage.NotSame -> stringResource(R.string.incorrect_pattern_drawn)
                }
            )

            Spacer(modifier = Modifier.height(MaterialTheme.padding.large * 2))

            PasswordPattern(
                onStart = {
                    onEvent(LoginPatternContract.Event.DrawPatternStart)
                },

                onResult = {
                    onEvent(
                        LoginPatternContract.Event.DrawPatternFinished(pattern = it)
                    )
                }
            )

            Spacer(modifier = Modifier.height(MaterialTheme.padding.large * 2))


            AnimatedVisibility(
                visible = state.showRemainingAttempts
            ) {
                EnterPatternAttemptText(attempts = state.remainingAttempts)
            }

        }

    }
}


@Preview
@Composable
private fun EnterPatternContentPreview() {
    WalletLineTheme {
        EnterPatternContent(
            state = LoginPatternState(),
            onEvent = {}
        )

    }
}

