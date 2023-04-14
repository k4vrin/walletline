package com.walletline.android.presentation.screens.onboarding.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.padding
import com.walletline.android.presentation.util.Constants
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingSliderCard(
    slideTitle: String,
    slideContent: String,
    pagerState: PagerState
) {
    val coroutineScope = rememberCoroutineScope()

    SliderCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.padding.medium)
    ) {

        Spacer(modifier = Modifier.height(Dimen.SlideTitleTopMargin))

        Text(
            text = slideTitle,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = Dimen.SlideTitleHPadding)
        )

        Spacer(modifier = Modifier.height(Dimen.SlideContentTopMargin))

        Text(
            text = slideContent,
            style = MaterialTheme.typography.bodyLarge,
//                color = MaterialTheme.colorScheme.onSurface,
            color = Color(0xFF13095E).copy(alpha = 0.8f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = Dimen.SlideContentHPadding)
        )

        Spacer(modifier = Modifier.height(Dimen.DotsIndicatorTopMargin))

        DotsIndicator(
            totalDots = Constants.SliderPageCount,
            selectedIndex = pagerState.currentPage,
            selectedColor = MaterialTheme.colorScheme.primary,
            unSelectedColor = Color(0xFFD9D9D9),
        )
        Spacer(modifier = Modifier.height(Dimen.NextButtonTopMargin))

        OnBoardingCardButton(height = Dimen.NextButtonHeight, text = "Next",
            modifier = Modifier
                .padding(
                    start = Dimen.NextButtonHMargin,
                    end = Dimen.NextButtonHMargin,
                    bottom = Dimen.NextButtonBottomMargin
                )
                .align(Alignment.End),
            onClick = {
                coroutineScope.launch {
                    // Call scroll to on pagerState
                    if (pagerState.currentPage < Constants.SliderPageCount) {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    } else {
                        // go to next page
                    }

                }
            })

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OnBoardingSliderCard(
        "You ought to know where your money goes",
        "Get an overview of how you are performing and motivate yourself to achieve even more.",
        rememberPagerState()
    )
}
