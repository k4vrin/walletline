package com.walletline.android.presentation.screens.intro.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.R
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.model.OnBoardingPage
import com.walletline.android.presentation.screens.intro.onboarding.components.OnBoardingCard
import com.walletline.android.presentation.screens.intro.onboarding.components.SkipButton
import com.walletline.android.presentation.screens.intro.IntroContract
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.padding
import com.walletline.presentation.screens.auth.admission.IntroState
import kotlinx.coroutines.launch


@Composable
fun OnBoardingContent(
    state: IntroState,
    onEvent: (IntroContract.Event) -> Unit,
) {

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val pages = remember {
        listOf(
            OnBoardingPage(
                image = R.drawable.im_onboarding_1,
                title = R.string.boarding_title_1,
                description = R.string.boarding_desc_1
            ),
            OnBoardingPage(
                image = R.drawable.im_onboarding_2,
                title = R.string.boarding_title_2,
                description = R.string.boarding_desc_2
            ),
            OnBoardingPage(
                image = R.drawable.im_onboarding_3,
                title = R.string.boarding_title_3,
                description = R.string.boarding_desc_3
            )
        )
    }

    WalletLineBackground {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.height(MaterialTheme.padding.smallLarge))

            SkipButton(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(MaterialTheme.padding.medium),
                onClick = {onEvent(IntroContract.Event.UserOnBoarded)}
            )


            HorizontalPager(
                pageCount = pages.count(),
                state = pagerState,
                userScrollEnabled = true
            ) { page ->
                val src = pages[page]
                Image(
                    painter = painterResource(src.image),
                    contentDescription = stringResource(R.string.desc_boarding_image),
                    modifier = Modifier
                        .height(Dimen.SliderImageHeight)
                        .aspectRatio(1f),
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.padding.smallLarge))

            OnBoardingCard(
                modifier = Modifier
                    .padding(MaterialTheme.padding.medium),
                title = stringResource(id = pages[pagerState.currentPage].title),
                description = stringResource(id = pages[pagerState.currentPage].description),
                currentPage = pagerState.currentPage
            ) {
                scope.launch {
                    if (pagerState.currentPage < pages.count() - 1) {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    } else {
                       onEvent(IntroContract.Event.UserOnBoarded)
                    }
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.padding.medium))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    //OnBoardingContent {}
}