package com.walletline.android.presentation.screens.wallet.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.walletline.android.presentation.components.WalletLineBackground


class RoundedRectOutlinedCorner(
    private val cornerRadius: Dp = 36.dp,
    private val cutOutHeight: Dp = 48.dp,
    private val cutOutWidth: Dp = 48.dp
) : Shape {
    override fun createOutline(
        size: Size, layoutDirection: LayoutDirection, density: Density
    ): Outline {
        return Outline.Generic(Path().apply {
            val cornerRadius = with(density) { cornerRadius.toPx() }
            val cutOutHeight = with(density) { cutOutHeight.toPx() }
            val cutOutWidth = with(density) { cutOutWidth.toPx() }


            arcTo(
                rect = Rect(offset = Offset(0f, cutOutHeight), size =  Size(cornerRadius, cornerRadius)),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )
            lineTo(cutOutWidth - cornerRadius, cutOutHeight)
            arcTo(
                rect = Rect(
                    offset = Offset(cutOutWidth - cornerRadius, cutOutHeight - cornerRadius),
                    Size(cornerRadius, cornerRadius)
                ), startAngleDegrees = 90f, sweepAngleDegrees = -90f, forceMoveTo = false
            )
            lineTo(cutOutWidth, 0f)
            arcTo(
                rect = Rect(offset = Offset(cutOutWidth, 0f), size =  Size(cornerRadius, cornerRadius)),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )
            lineTo(size.width - cornerRadius, 0f)
            arcTo(
                rect = Rect(
                    offset = Offset(size.width - cornerRadius, 0f),
                    Size(cornerRadius, cornerRadius)
                ), startAngleDegrees = 270.0f, sweepAngleDegrees = 90f, forceMoveTo = false
            )
            lineTo(size.width, size.height - cornerRadius)
            arcTo(
                rect = Rect(
                    offset = Offset(size.width - cornerRadius, size.height - cornerRadius),
                    Size(cornerRadius, cornerRadius)
                ), startAngleDegrees = 0f, sweepAngleDegrees = 90f, forceMoveTo = false
            )

            lineTo(cornerRadius, size.height)
            arcTo(
                rect = Rect(
                    offset = Offset(0f, size.height - cornerRadius),
                    Size(cornerRadius, cornerRadius)
                ), startAngleDegrees = 90f, sweepAngleDegrees = 90f, forceMoveTo = false
            )

            close()
        }

        )
    }
}

@Preview
@Composable
fun RoundedRectOutlinedCornerPreviewTheme() {
    WalletLineBackground(
        Modifier.clip(RoundedRectOutlinedCorner())
    ) {

    }
}