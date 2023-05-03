package com.walletline.android.presentation.screens.auth.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding

@Composable
fun FadingDotLoading(
    modifier: Modifier = Modifier,
    animationDuration: Int = 1000,
    totalDots: Int = 3,
    dotSize: Dp = MaterialTheme.padding.smallMedium,
    spaceBetweenDots: Dp = MaterialTheme.padding.extraSmall,
    dotColor: Color = MaterialTheme.customColor.neutrals.main
) {
    val animationDurationFactor = remember { animationDuration / 4 }
    val startOffsetFactor = remember { animationDuration / totalDots }

    val dots = List(totalDots) { index ->

        var animatedValue by remember { mutableStateOf(0f) }

        LaunchedEffect(Unit) {

            animate(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = animationDuration
                        delayMillis = animationDurationFactor
                        0f at 0 with LinearOutSlowInEasing
                        0.5f at animationDurationFactor with LinearOutSlowInEasing
                        1f at animationDurationFactor * 2 with LinearOutSlowInEasing
                        0f at animationDurationFactor * 3 with LinearOutSlowInEasing
                        0f at animationDuration with LinearOutSlowInEasing
                    },
                    repeatMode = RepeatMode.Restart,
                    initialStartOffset = StartOffset(offsetMillis = startOffsetFactor * index)
                )
            ) { value, _ -> animatedValue = value }
        }
        animatedValue
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetweenDots),
        verticalAlignment = Alignment.CenterVertically
    ) {

        repeat(totalDots) { index ->
            Box(
                modifier = Modifier
                    .size(dotSize)
                    .graphicsLayer { alpha = dots[index] }
                    .background(
                        color = dotColor,
                        shape = CircleShape
                    )
            )
        }

    }
}

@Preview
@Composable
fun FadingDotLoadingPrev() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        FadingDotLoading()
    }
}