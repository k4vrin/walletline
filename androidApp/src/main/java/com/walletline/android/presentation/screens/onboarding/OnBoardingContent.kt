package com.walletline.android.presentation.screens.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.screens.onboarding.components.OnBoardingButton
import com.walletline.android.presentation.screens.onboarding.components.OnBoardingSlider
import com.walletline.android.presentation.theme.Dimen


@Composable
fun OnBoardingContent(
    onNextScreenClick: () -> Unit
) {
    WalletLineBackground {
        Column(
            modifier = Modifier
                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(Dimen.SkipButtonTopMargin))

            OnBoardingButton(width = Dimen.SkipButtonWidth,
                height = Dimen.SkipButtonHeight,
                text = "Skip",
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(Dimen.ButtonHMargin),
                onClick = {
                    // navigate to next page
                    onNextScreenClick()
                })

            OnBoardingSlider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OnBoardingContent {}
}