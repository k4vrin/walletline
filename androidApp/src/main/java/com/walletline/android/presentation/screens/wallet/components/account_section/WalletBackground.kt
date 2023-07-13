package com.walletline.android.presentation.screens.wallet.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.util.DevicesPreviews
import com.walletline.android.presentation.util.ThemePreviews
import kotlin.math.max

@Composable
fun WalletBackground(
    modifier: Modifier = Modifier,
    isScrollEnabled: Boolean = true,
    content: @Composable BoxScope.() -> Unit,
) {
    val scrollState = rememberScrollState()
    val color = MaterialTheme.customColor.neutrals.main
    Box(
        modifier = modifier
            .height(250.dp)
            .clip(RoundedRectOutlinedCorner())
            .background(MaterialTheme.customColor.main.five)
            //.clip(RoundedCornerShape(20.dp))
            .drawBehind {
                val length = max(size.height, size.width)
                val bigCircleRadius = length / 2.2f
                val smallCircleRadius = length / 3.5f
            /*    drawCircle(
                    color = Color.White,
                    radius = 24f,
                    alpha = 1f,
                    center = Offset(x = 0f ,y = 0f)
                )*/
                drawCircle(
                    color = color,
                    radius = bigCircleRadius,
                    alpha = 0.04f,
                    center = Offset(x = bigCircleRadius / 10 ,y = bigCircleRadius + (bigCircleRadius / 2f))
                )
                drawCircle(
                    color = color,
                    radius = smallCircleRadius,
                    alpha = 0.04f,
                    center = Offset(x = smallCircleRadius / 6, y = smallCircleRadius / 2f)
                )
            }
            .verticalScroll(state = scrollState, enabled = isScrollEnabled)
    ) {
        content()
    }
}

@ThemePreviews
@DevicesPreviews
@Composable
fun WalletBackgroundPreviewTheme() {
    WalletLineTheme {
        WalletBackground {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "Hello Walletline",
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}