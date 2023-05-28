package com.walletline.android.presentation.screens.intro.onboarding.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.screens.auth.components.AuthCard
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding
import com.walletline.android.presentation.util.sdp

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    selectedColor: Color = MaterialTheme.customColor.main.main,
    unSelectedColor: Color = MaterialTheme.customColor.main.two,
) {


    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.sdp),
        verticalAlignment = Alignment.CenterVertically
    ) {


        repeat(totalDots) { index ->
            val isSelected = selectedIndex == index
            val height by animateDpAsState(
                targetValue = if (isSelected) 12.sdp else 8.sdp,
                animationSpec = tween(
                    durationMillis = 300
                )
            )
            Box(
                modifier = Modifier
                    .width(8.sdp)
                    .height(height)
                    .background(
                        color = if (isSelected) selectedColor
                        else unSelectedColor,
                        shape = RoundedCornerShape(50)
                    ),
            )
        }
    }
}

@Preview
@Composable
fun DotsIndicatorPreview() {
    WalletLineTheme {
        WalletLineBackground {
            AuthCard(
                modifier = Modifier
                    .height(200.dp)
            ) {
                DotsIndicator(
                    modifier = Modifier
                        .padding(MaterialTheme.padding.extraMedium),
                    totalDots = 3,
                    selectedIndex = 1,
                    selectedColor = MaterialTheme.customColor.main.main,
                    unSelectedColor = MaterialTheme.customColor.main.two
                )
            }
        }
    }
}