package com.walletline.android.presentation.screens.onboarding.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.walletline.android.R
import com.walletline.android.presentation.theme.Dimen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingSliderContent(
    slideImageResource: Int,
    slideTitleText: String,
    slideContentText: String,
    pagerState: PagerState
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            painterResource(slideImageResource),
            contentDescription = "",
            modifier = Modifier
                .height(Dimen.SliderImageHeight)
                .fillMaxWidth(),

            )
        Spacer(modifier = Modifier.height(Dimen.CardTopMargin))

        OnBoardingSliderCard(slideTitleText, slideContentText, pagerState = pagerState)
    }

}