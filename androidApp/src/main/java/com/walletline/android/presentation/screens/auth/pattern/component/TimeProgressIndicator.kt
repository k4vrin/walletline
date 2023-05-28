package com.walletline.android.presentation.screens.auth.pattern.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.util.secondsToMinuteAndSeconds
import com.walletline.android.presentation.util.ssp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlin.math.*

@Composable
fun TimeProgressIndicator(
    timeRemain: Int,
    modifier: Modifier = Modifier,
    maxValueInSeconds: Int = 60,
    indicatorBackgroundColor: Color = MaterialTheme.customColor.neutrals.main.copy(alpha = 0.2f),
    indicatorForegroundColor: Color = MaterialTheme.customColor.neutrals.main,
    indicatorWidth: Float = 8f,
    circleRadius: Float = 15f,
) {

    var timePassed by remember {
        mutableStateOf(0.0)
    }

    LaunchedEffect(timeRemain) {
        timePassed = (maxValueInSeconds - timeRemain).toDouble()
    }

    // percentage of passed time over a full circle
    val targetValue = remember(timePassed) {
        (timePassed / maxValueInSeconds) * (Math.PI * 2)
    }

    val animValue by animateFloatAsState(
        targetValue = targetValue.toFloat(),
    )


    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .aspectRatio(1f)
        ) {

            val radius = size.width / 2

            drawArc(
                size = size,
                color = indicatorBackgroundColor,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(
                    width = indicatorWidth,
                    join = StrokeJoin.Round,
                    cap = StrokeCap.Round
                )
            )

            val degree = Math.toDegrees(animValue.toDouble()).toFloat()

            drawArc(
                size = size,
                color = indicatorForegroundColor,
                startAngle = -90f,
                sweepAngle = degree,
                useCenter = false,
                style = Stroke(
                    width = indicatorWidth,
                    join = StrokeJoin.Round,
                    cap = StrokeCap.Round
                )
            )

            val circleCenter = kotlin.run {
                val y = (cos(Math.PI - animValue) * radius) + radius
                val x = (sin(Math.PI - animValue) * radius) + radius

                Offset(x.toFloat(), y.toFloat())
            }

            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        indicatorForegroundColor,
                        indicatorForegroundColor.copy(alpha = 0.3f),
                        indicatorForegroundColor.copy(alpha = 0.2f),
                        indicatorForegroundColor.copy(alpha = 0.1f),
                        indicatorForegroundColor.copy(alpha = 0.05f),
                        Color.Transparent,
                    ),
                    center = circleCenter,
                    radius = circleRadius * 3f,
                    tileMode = TileMode.Mirror
                ),
                radius = circleRadius * 3f,
                center = circleCenter,
            )

            drawCircle(
                color = indicatorForegroundColor,
                radius = circleRadius,
                center = circleCenter
            )

        }


        AnimatedContent(targetState = timeRemain) { targetTime ->
            Text(
                text = secondsToMinuteAndSeconds(targetTime.toLong()),
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 32.ssp),
                color = MaterialTheme.customColor.neutrals.main
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun TimeProgressIndicatorPreview() {
    var time by remember {
        mutableStateOf(0)
    }
    LaunchedEffect(Unit) {
        flow {
            var a = 60
            emit(a)
            while (isActive && a > 0) {
                a--
                delay(1000)
                emit(a)
            }
        }
            .collect {
                time = it
            }
    }
    WalletLineTheme {
        WalletLineBackground {
            TimeProgressIndicator(
                modifier = Modifier
                    .padding(24.dp)
                    .size(250.dp)
                    .background(Color.Transparent),
                timeRemain = time,
            )
        }
    }
}
