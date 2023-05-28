package com.walletline.android.presentation.screens.auth.pattern.component.pattern_model

import android.view.MotionEvent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.tooling.preview.Preview
import co.touchlab.kermit.Logger
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ComposeLockMain(
    modifier: Modifier,
    dotsInRow: Int,
    sensitivity: Float,
    dotsDefaultColor: Color,
    dotsSecondColor: Color,
    dotSize: Float,
    linesColor: Color,
    linesStroke: Float,
    animationDuration: Int = 200,
    animationDelay: Long = 100,
    arrowDegree: Double = 30.0,
    onResult: (result: List<Dot>, dotIdAsPassword: String) -> Unit,
    onStart: (dot: Dot) -> Unit,
    onDotConnected: (dot: Dot) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val defaultDots = remember {
        mutableListOf<Dot>()
    }

    val selectedDotColor = remember {
        mutableStateOf(0f)
    }

    var previewLine by remember {
        mutableStateOf(Line(start = Offset.Unspecified, end = Offset.Unspecified))
    }
    val connectedLines = remember {
        mutableListOf<Line>()
    }
    val connectedDots = remember {
        mutableListOf<Dot>()
    }
    Canvas(
        modifier
            .pointerInteropFilter { motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        connectedLines.clear()
                        connectedDots.clear()
                        defaultDots.forEach { dot ->
                            dot.color = dotsDefaultColor
                        }

                        defaultDots.forEach { dot ->

                            if (!motionEvent.isInRangeOf(
                                    dot = dot,
                                    sensitivity = sensitivity
                                )
                            ) return@forEach

                            connectedDots.add(dot)
                            onStart(dot)

                            scope.launch {
                                dot.animatebleSize.animateTo(
                                    (dotSize * 1.5).toFloat(),
                                    tween(animationDuration)
                                )
                                selectedDotColor.value = 0.2f
                                dot.color = dotsSecondColor
                                delay(animationDelay)
                                dot.animatebleSize.animateTo(dotSize, tween(animationDuration))
                            }
                            previewLine = previewLine.copy(
                                start = Offset(
                                    dot.offset.x,
                                    dot.offset.y
                                )
                            )
                        }
                    }

                    MotionEvent.ACTION_MOVE -> {
                        previewLine = previewLine.copy(end = Offset(motionEvent.x, motionEvent.y))
                        defaultDots.forEach { dot ->
                            if (
                                connectedDots.contains(dot) ||
                                !motionEvent.isInRangeOf(dot = dot, sensitivity = sensitivity)
                            ) return@forEach

                            Logger.d(tag = "ComposeLock") {
                                "default dots: $defaultDots"
                            }

                            connectedLines.add(
                                Line(
                                    start = previewLine.start,
                                    end = dot.offset
                                )
                            )

                            connectedDots.add(dot)
                            onDotConnected(dot)
                            scope.launch {
                                dot.animatebleSize.animateTo(
                                    (dotSize * 1.5).toFloat(),
                                    tween(animationDuration)
                                )
                                selectedDotColor.value = 0.2f
                                dot.color = dotsSecondColor
                                delay(animationDelay)
                                dot.animatebleSize.animateTo(
                                    dotSize,
                                    tween(animationDuration)
                                )
                            }
                            previewLine = previewLine.copy(start = dot.offset)

                        }
                    }

                    MotionEvent.ACTION_UP -> {
                        previewLine = Line(start = Offset.Unspecified, end = Offset.Unspecified)
                        onResult(
                            connectedDots,
                            connectedDots.joinToString(separator = "") { dot -> dot.id.toString() }
                        )
                    }
                }
                true
            }
    ) {

        val canvasSize = size
        val spaceBetweenDotsInRow = (canvasSize.width / (dotsInRow - 1)) - sensitivity
        val spaceBetweenDotsInColumn = (canvasSize.height / (dotsInRow - 1)) - sensitivity

        if (defaultDots.size < dotsInRow * dotsInRow) {
            repeat(dotsInRow) { row ->
                repeat(dotsInRow) { col ->
                    val dotOffset = Offset(
                        (spaceBetweenDotsInRow * col) + sensitivity,
                        (spaceBetweenDotsInColumn * row) + sensitivity
                    )
                    defaultDots.add(
                        Dot(
                            defaultDots.size + 1,
                            dotOffset,
                            Animatable(dotSize),
                            dotsDefaultColor
                        )
                    )
                }
            }
        }

        if (previewLine.isSpecified())
            drawArrowLine(previewLine, arrowDegree, linesColor, linesStroke)


        defaultDots.forEach { dot ->
            drawCircle(
                color = dot.color,
                radius = dot.animatebleSize.value,
                center = dot.offset,
            )

            if (!connectedDots.contains(dot)) return@forEach

            drawCircle(
                color = dot.color.copy(alpha = selectedDotColor.value),
                radius = dot.animatebleSize.value + 10,
                center = dot.offset,
            )

        }

        connectedLines.forEach { line ->

            if (line.isSpecified() && line.start != Offset.Zero) {

                drawArrowLine(
                    line = line,
                    arrowDegree = arrowDegree,
                    linesColor = linesColor,
                    linesStroke = linesStroke
                )
            }


        }

    }
}

private fun DrawScope.drawArrowLine(
    line: Line,
    arrowDegree: Double,
    linesColor: Color,
    linesStroke: Float,
) {
    val middlePoint = (line.start + line.end) / 2f
    val arrowTailLength = 20f
    val diffX = line.end.x - line.start.x
    val diffY = line.end.y - line.start.y
    val rightToLeftRadian = if (diffX < 0) Math.PI else 0.0
    val linePivotRadian = rightToLeftRadian + atan(diffY / diffX)


    val arrowPoint1 = kotlin.run {
        val x =
            cos(Math.PI - linePivotRadian - Math.toRadians(arrowDegree)) * arrowTailLength
        val y =
            sin(Math.PI - linePivotRadian - Math.toRadians(arrowDegree)) * arrowTailLength
        Offset(x.toFloat(), y.toFloat())
    }
    val arrowPoint2 = kotlin.run {
        val x =
            cos(Math.PI - linePivotRadian + Math.toRadians(arrowDegree)) * arrowTailLength
        val y =
            sin(Math.PI - linePivotRadian + Math.toRadians(arrowDegree)) * arrowTailLength
        Offset(x.toFloat(), y.toFloat())
    }
    val linePath = Path().apply {
        moveTo(x = line.start.x, y = line.start.y)
        lineTo(x = line.end.x, y = line.end.y)
        close()
    }

    val arrowPath = Path().apply {
        moveTo(x = middlePoint.x, y = middlePoint.y)
        lineTo(x = middlePoint.x + arrowPoint1.x, y = middlePoint.y - arrowPoint1.y)
        lineTo(x = middlePoint.x + arrowPoint2.x, y = middlePoint.y - arrowPoint2.y)
        lineTo(x = middlePoint.x, y = middlePoint.y)
        close()
    }
    drawPath(
        color = linesColor,
        path = linePath,
        style = Stroke(
            width = linesStroke,
            cap = StrokeCap.Round,
            join = StrokeJoin.Round
        )
    )

    drawPath(
        color = linesColor,
        path = arrowPath,
        style = Fill
    )

}

private fun MotionEvent.isInRangeOf(dot: Dot, sensitivity: Float) =
    this.x in (dot.offset.x - sensitivity)..(dot.offset.x + sensitivity) &&
            this.y in (dot.offset.y - sensitivity)..(dot.offset.y + sensitivity)


@Preview
@Composable
fun ComposeLockPreview() {

    WalletLineTheme {
        WalletLineBackground {
            ComposeLockMain(
                modifier = Modifier
                    .size(Dimen.PasswordPatternSize),
                dotsInRow = 3,
                sensitivity = 80f,
                dotsDefaultColor = MaterialTheme.customColor.neutrals.main,
                dotsSecondColor = MaterialTheme.customColor.neutrals.main,
                dotSize = 10f,
                linesColor = MaterialTheme.customColor.neutrals.main,
                linesStroke = 5f,
                animationDuration = 200,
                animationDelay = 100,
                onResult = { dots, pass ->

                },
                onStart = { dot ->

                },
                onDotConnected = {}
            )

        }

    }
}