package com.walletline.android.presentation.screens.onboarding


import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph


@RootNavGraph(start = true)
@Destination
@Composable
fun OnBoardingScreen() {
    OnBoardingContent()
}

