package com.walletline.android.presentation.screens.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.walletline.android.presentation.theme.Dimen

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color,
) {

    LazyRow(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()

    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(width = Dimen.SelectedDotsSize, height = Dimen.UnSelectedDotsHeight)
                        .clip(CircleShape)
                        .background(selectedColor)
                        .offset(y = Dimen.SelectedDotsTopMargin)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(Dimen.SelectedDotsSize)
                        .clip(CircleShape)
                        .background(unSelectedColor)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = Dimen.DotsPadding))
            }
        }
    }
}