package com.walletline.android.presentation.screens.onboarding.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.walletline.android.R
import com.walletline.android.presentation.util.Constants

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingSlider() {
    val pagerState = rememberPagerState()
    var slideImage = remember { mutableStateOf(R.drawable.im_onboarding_1) }
    var slideTitle = remember { mutableStateOf("You ought to know where your money goes") }
    var slideContent =
        remember { mutableStateOf("Get an overview of how you are performing and motivate yourself to achieve even more.") }

    HorizontalPager(pageCount = Constants.SliderPageCount, state = pagerState, userScrollEnabled = false) { page ->
        when (page) {

            0 -> {
                slideImage.value = R.drawable.im_onboarding_1
                slideTitle.value = "You ought to know where your money goes1"
                slideContent.value =
                    "Get an overview of how you are performing and motivate yourself to achieve even more."
            }

            1 -> {
                slideImage.value = R.drawable.im_onboarding_2
                slideTitle.value = "Gain total \n" +
                        "control of your money"
                slideContent.value =
                    "Track your transaction easily, with categories and financial report"
            }

            2 -> {
                slideImage.value = R.drawable.im_onboarding_3
                slideTitle.value = "Plan ahead and manage your money better"
                slideContent.value =
                    "Setup your budget for each category\n" +
                            "so you in control. Track categories you spend the most money on"
            }
        }

        OnBoardingSliderContent(
            slideImageResource = slideImage.value,
            slideTitleText = slideTitle.value,
            slideContentText = slideContent.value,
            pagerState = pagerState
        )
    }

}