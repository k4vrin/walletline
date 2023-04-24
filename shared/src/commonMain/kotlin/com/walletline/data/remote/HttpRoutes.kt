package com.walletline.data.remote

object HttpRoutes {
    const val Host = "https://walletline1.datarivers.org/api/v1"
    const val Register = "${Host}/auth/register"
    const val GetOTP = "${Host}/auth/otp/get"
}