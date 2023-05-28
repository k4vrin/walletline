package com.walletline.android.presentation.screens.auth.pattern.make_pattern.create_pattern

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.R
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.screens.auth.components.OrDivider
import com.walletline.android.presentation.screens.auth.pattern.component.IgnoreSettingPatternSection
import com.walletline.android.presentation.screens.auth.pattern.component.PatternButton
import com.walletline.android.presentation.screens.auth.pattern.component.PatternRememberText
import com.walletline.android.presentation.screens.auth.pattern.component.PatternTitleText
import com.walletline.android.presentation.screens.auth.pattern.component.SensorRecognitionButton
import com.walletline.android.presentation.screens.auth.pattern.component.pattern_model.PasswordPattern
import com.walletline.android.presentation.screens.auth.pattern.make_pattern.MakePatternContract
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.padding
import com.walletline.presentation.screens.auth.pattern.make_pattern.MakePatternState

@Composable
fun CreatePatternContent(
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

            PatternRememberText(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.padding.medium),
                text = stringResource(R.string.rememberPattern)
            )

            Spacer(modifier = Modifier.height(MaterialTheme.padding.large))

            PatternTitleText(text = stringResource(R.string.createYourPattern))

            Spacer(modifier = Modifier.height(MaterialTheme.padding.extraMedium))

            PasswordPattern(
                onStart = {
                    onEvent(
                        MakePatternContract.Event.PatternChanged(
                            pattern = "",
                            isScrollEnabled = false
                        )
                    )
                },
                onResult = {
                    onEvent(
                        MakePatternContract.Event.PatternChanged(
                            pattern = it,
                            isScrollEnabled = true
                        )
                    )
                }
            )

            Spacer(modifier = Modifier.height(MaterialTheme.padding.extraLarge))

            PatternButton(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.padding.medium),
                text = stringResource(id = R.string.continueButton),
                enabled = state.isContinueButtonEnable,
                onClick = {
                    onEvent(MakePatternContract.Event.ContinueClicked)
                }
            )

            Spacer(modifier = Modifier.height(MaterialTheme.padding.smallLarge))

            OrDivider(
                text = stringResource(R.string.or_continue_with),
                modifier = Modifier
                    .padding(MaterialTheme.padding.medium)
            )

            Spacer(modifier = Modifier.height(MaterialTheme.padding.smallLarge))

            SensorRecognitionButton(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.padding.medium),
                onClick = { onEvent(MakePatternContract.Event.FingerPrintClicked) }
            )

            Spacer(modifier = Modifier.height(MaterialTheme.padding.extraMedium))

            IgnoreSettingPatternSection(
                onIgnoreClick = { onEvent(MakePatternContract.Event.SkipPatternClicked) }
            )

            Spacer(modifier = Modifier.height(MaterialTheme.padding.extraMedium))

        }

    }
}


@Preview
@Composable
fun CreatePatternPreview() {
    WalletLineTheme {
        CreatePatternContent(
            MakePatternState()
        ) {

        }
    }
}
