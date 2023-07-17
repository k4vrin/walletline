package com.walletline.android.presentation.theme.transition

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations

object WalletLineTransitionAnimation {
    val default = RootNavGraphDefaultAnimations(
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(
                    durationMillis = TransitionDurations.MEDIUM,
                    delayMillis = TransitionDurations.MEDIUM
                )
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(
                    durationMillis = TransitionDurations.MEDIUM,
                    delayMillis = TransitionDurations.MEDIUM
                )
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(
                    durationMillis = TransitionDurations.MEDIUM,
                    delayMillis = TransitionDurations.MEDIUM
                )
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(
                    durationMillis = TransitionDurations.MEDIUM,
                    delayMillis = TransitionDurations.MEDIUM
                )
            )
        }
    )
}