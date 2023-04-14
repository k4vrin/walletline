package com.walletline.android.presentation.screens.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.screens.onboarding.components.OnBoardingButton
import com.walletline.android.presentation.screens.onboarding.components.OnBoardingSlider
import com.walletline.android.presentation.theme.Dimen


@Composable
fun OnBoardingContent() {
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
                })

            OnBoardingSlider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OnBoardingContent()
}