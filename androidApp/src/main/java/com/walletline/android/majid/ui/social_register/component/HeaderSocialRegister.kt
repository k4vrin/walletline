package com.walletline.android.android.presentation.wallet.social_register.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.R
import com.walletline.android.android.presentation.wallet.mobile_number.mn_font_enterYour
import com.walletline.android.android.presentation.wallet.mobile_number.mn_font_mobileNumber
import com.walletline.android.android.presentation.wallet.multiStyleText
import com.walletline.android.android.presentation.wallet.theme.BLUE2
import com.walletline.android.android.presentation.wallet.theme.Black_1C
import com.walletline.android.android.presentation.wallet.theme.Gray_60
import com.walletline.android.android.presentation.wallet.theme.Green

@Preview
@Composable
fun HeaderSocialRegister()
{
    Column(horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(
            stringResource(id = R.string.accessByYour),
            fontSize = mn_font_enterYour,
            fontWeight = FontWeight(700),
            color = Gray_60,
            modifier = Modifier )


        Text(
            text = multiStyleText(
                stringResource(R.string.social),
                BLUE2,
                Black_1C,
                stringResource(R.string.media),
                Green,
                Black_1C
            ),
            fontSize = mn_font_mobileNumber,
            fontWeight = FontWeight(700),
            color = Black_1C,
            modifier = Modifier )

    }

}