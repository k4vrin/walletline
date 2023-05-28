package com.walletline.android.presentation.screens.auth.pattern.login_pattern.progressbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.R
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.screens.auth.pattern.component.PatternTitleText
import com.walletline.android.presentation.screens.auth.pattern.component.TimeProgressIndicator
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.padding
import com.walletline.android.presentation.util.sdp
import com.walletline.presentation.screens.auth.pattern.login_pattern.LoginPatternState

@Composable
fun LockContent(
    state: LoginPatternState,
) {

    WalletLineBackground {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Spacer(modifier = Modifier.height(MaterialTheme.padding.large * 2))

            Image(painterResource(id = R.drawable.timer), contentDescription = null)

            Spacer(modifier = Modifier.height(MaterialTheme.padding.smallLarge))

            PatternTitleText(
                text = stringResource(R.string.appLockError),
            )

            Spacer(modifier = Modifier.height(MaterialTheme.padding.large * 2))

            TimeProgressIndicator(
                modifier = Modifier
                    .size(211.sdp),
                timeRemain = state.lockTimer
            )


        }

    }
}


@Preview
@Composable
private fun ProgressbarContentPreview() {
    WalletLineTheme {
        LockContent(
            state = LoginPatternState(),
        )
    }
}