package com.walletline.android.presentation.util

import java.util.concurrent.TimeUnit

fun secondsToMinuteAndSeconds(seconds: Long): String {
    return String.format(
        "%d:%02d",
        TimeUnit.SECONDS.toMinutes(seconds),
        TimeUnit.SECONDS.toSeconds(seconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(seconds))
    )
}