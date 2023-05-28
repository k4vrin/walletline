package com.walletline.android.presentation.screens.intro.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.screens.auth.components.WalletlineHeader
import com.walletline.android.presentation.screens.intro.IntroContract
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding
import com.walletline.android.presentation.util.ThemePreviews
import com.walletline.presentation.screens.auth.admission.IntroState

@Composable
fun SplashContent(
    state: IntroState,
    onEvent: (IntroContract.Event) -> Unit,
) {

    WalletLineBackground {

        WalletlineHeader(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = Dimen.WalletHeaderTopMargin)
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = MaterialTheme.padding.large),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CircularProgressIndicator()


            Text(
                text = "Version 1.0",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.customColor.neutrals.main,
                textAlign = TextAlign.Center,
            )
        }

    }

}


@ThemePreviews
@Composable
fun SplashScreenPreviewTheme() {
    WalletLineTheme {
        SplashContent(
            state = IntroState()
        ) {

        }
    }
}
