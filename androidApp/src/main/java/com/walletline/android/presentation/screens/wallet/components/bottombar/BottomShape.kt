package com.walletline.android.presentation.screens.wallet.components.bottombar


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.walletline.android.presentation.components.WalletLineBackground

class BottomShape(
    private val cornerRadius: Dp = 50.dp,
    //private val curveCircleRadius: Float = 70f,
    private val curveCircleRadius: Float = 30f,
) : Shape {
    override fun createOutline(
        size: Size, layoutDirection: LayoutDirection, density: Density,
    ): Outline {
        return Outline.Generic(
            Path().apply {
                val cornerRadius = with(density) { cornerRadius.toPx() }
                val cutoutHeight = 18f
                val curveThreshold = (curveCircleRadius * 2) + (curveCircleRadius / 3)
                val middlePoint = Offset(x = (size.width / 2f), y = cutoutHeight)
                val firstControlPoint =
                    Offset(x = middlePoint.x - curveCircleRadius, y = cutoutHeight)
                val secondControlPoint = Offset(x = middlePoint.x - curveCircleRadius, y = 0f)
                val peak = Offset(x = middlePoint.x, y = 0f)
                val thirdControlPoint = Offset(x = middlePoint.x + curveCircleRadius, y = 0f)
                val forthControlPoint =
                    Offset(x = middlePoint.x + curveCircleRadius, y = cutoutHeight)
                val fifthControlPoint = Offset(x = middlePoint.x + curveThreshold, y = cutoutHeight)

                arcTo(
                    rect = Rect(
                        offset = Offset(
                            x = 0f,
                            y = cutoutHeight
                        ),
                        size = Size(
                            width = cornerRadius,
                            height = cornerRadius
                        )
                    ),
                    startAngleDegrees = 180f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )

                lineTo(
                    x = middlePoint.x - curveThreshold,
                    y = cutoutHeight
                )

                cubicTo(
                    x1 = firstControlPoint.x,
                    y1 = firstControlPoint.y,
                    x2 = secondControlPoint.x,
                    y2 = secondControlPoint.y,
                    x3 = peak.x,
                    y3 = peak.y
                )

                cubicTo(
                    x1 = thirdControlPoint.x,
                    y1 = thirdControlPoint.y,
                    x2 = forthControlPoint.x,
                    y2 = forthControlPoint.y,
                    x3 = fifthControlPoint.x,
                    y3 = fifthControlPoint.y
                )

                lineTo(
                    x = size.width - cornerRadius,
                    y = cutoutHeight
                )

                arcTo(
                    rect = Rect(
                        offset = Offset(
                            x = size.width - cornerRadius,
                            y = cutoutHeight
                        ),
                        Size(
                            width = cornerRadius,
                            height = cornerRadius
                        )
                    ),
                    startAngleDegrees = 270.0f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )

                lineTo(
                    x = size.width,
                    y = size.height - cornerRadius
                )

                arcTo(
                    rect = Rect(
                        offset = Offset(
                            x = size.width - cornerRadius,
                            y = size.height - cornerRadius
                        ),
                        Size(
                            width = cornerRadius,
                            height = cornerRadius
                        )
                    ),
                    startAngleDegrees = 0f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )

                lineTo(
                    x = cornerRadius,
                    y = size.height
                )

                arcTo(
                    rect = Rect(
                        offset = Offset(
                            x = 0f,
                            y = size.height - cornerRadius
                        ),
                        Size(
                            width = cornerRadius,
                            height = cornerRadius
                        )
                    ),
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )

                close()
            }
        )
    }
}

@Preview
@Composable
fun BottomShapePreviewTheme() {
    WalletLineBackground(
        Modifier.clip(BottomShape())
    ) {

    }
}