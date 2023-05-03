package com.walletline.android.presentation.screens.onboarding.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.R
import com.walletline.android.presentation.screens.auth.components.AuthButton
import com.walletline.android.presentation.screens.auth.components.AuthCard
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding
import com.walletline.android.presentation.util.Constants
import com.walletline.android.presentation.util.sdp

@Composable
fun OnBoardingCard(
    title: String,
    description: String,
    currentPage: Int,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
) {

    AuthCard(
        modifier = modifier
            .fillMaxWidth(),
    ) {

        Spacer(modifier = Modifier.height(MaterialTheme.padding.smallLarge))

        Column(
            modifier = Modifier
                .height(144.sdp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedContent(targetState = title) { titleState ->
                Text(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.padding.large),
                    text = titleState,
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.customColor.information.six,
                    textAlign = TextAlign.Center,
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.padding.extraMedium))


            AnimatedContent(targetState = description) { descState ->
                Text(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.padding.large),
                    text = descState,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.customColor.information.five,
                    textAlign = TextAlign.Center,
                )
            }
        }

        Spacer(modifier = Modifier.height(MaterialTheme.padding.extraMedium))

        DotsIndicator(
            modifier = Modifier
                .height(MaterialTheme.padding.medium),
            totalDots = Constants.SliderPageCount,
            selectedIndex = currentPage,
        )


        AuthButton(
            modifier = Modifier
                .padding(all = MaterialTheme.padding.extraMedium),
            text = stringResource(R.string.next),
            onClick = onButtonClick
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WalletLineTheme {
        OnBoardingCard(
            "You ought to know where your money goes",
            "Get an overview of how you are performing and motivate yourself to achieve even more.",
            currentPage = 2
        ){}
    }
}
