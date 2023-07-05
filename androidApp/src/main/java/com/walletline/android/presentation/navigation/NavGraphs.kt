package com.walletline.android.presentation.navigation

import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph


@RootNavGraph
@NavGraph
annotation class IntroNavGraph(
    val start: Boolean = false
)


@RootNavGraph
@NavGraph
annotation class AuthNavGraph(
    val start: Boolean = false
)

@RootNavGraph(start = true)
@NavGraph
annotation class WalletNavGraph(
    val start: Boolean = false
)
