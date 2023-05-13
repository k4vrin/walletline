package com.walletline.android.presentation.screens.auth.verify_email.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.walletline.android.R
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import java.util.concurrent.TimeUnit

@Composable
fun VerifyEmailTimer(
    timeRemainInSeconds: () -> Int,
) {

    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedContent(
            modifier = Modifier
                .alignByBaseline(),
            targetState = timeRemainInSeconds()
        ) { targetTime ->
            Text(
                text = secondsToMinuteAndSeconds(targetTime.toLong()),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.customColor.neutrals.six
            )
        }

        Spacer(modifier = Modifier.width(2.dp))

        Text(
            modifier = Modifier
                .alignByBaseline(),
            text = stringResource(R.string.left_as_remain),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.customColor.neutrals.six
        )
    }


}

fun secondsToMinuteAndSeconds(seconds: Long): String {
    return String.format(
        "%d:%02d",
        TimeUnit.SECONDS.toMinutes(seconds),
        TimeUnit.SECONDS.toSeconds(seconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(seconds))
    )
}


@Preview
@Composable
private fun VerifyEmailTimerPreview() {
    WalletLineTheme {
        VerifyEmailTimer {
            60
        }
    }
}