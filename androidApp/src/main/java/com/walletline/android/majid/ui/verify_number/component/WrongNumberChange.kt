package com.walletline.android.android.presentation.wallet.verify_number.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.R
import com.walletline.android.android.presentation.wallet.mobile_number.mn_font_desc
import com.walletline.android.android.presentation.wallet.theme.Black_CC
import com.walletline.android.android.presentation.wallet.theme.Green
import com.walletline.android.android.presentation.wallet.twoStyleText

@Preview
@Composable
fun WrongNumberChange()
{
    Row(modifier=Modifier.padding(top =
        dimensionResource(id = R.dimen.paddingLarge)))
    {
        Text(text = twoStyleText(stringResource(R.string.wrongNumber)
            , color1 = Black_CC,
            text2 = stringResource(R.string.changeIt),
            color2 = Green
        ),
            fontWeight = FontWeight(400),
            fontSize = mn_font_desc,//dimensionResource(id = R.dimen.testFont).value.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
        )


    }










}