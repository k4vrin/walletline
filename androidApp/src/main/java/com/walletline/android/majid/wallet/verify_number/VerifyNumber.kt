package com.walletline.android.android.presentation.wallet.verify_number

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.codingwithmitch.kmm_learning_mitch.android.presentation.theme.*
import com.walletline.android.R
import com.walletline.android.android.presentation.wallet.mobile_number.component.ButtonSubmit
import com.walletline.android.android.presentation.wallet.verify_number.component.*

@Preview
@Composable
fun VerifyNumber(onProceedClick:()->Unit)
{
    Column(modifier= Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(color = Background)
        .padding(dimensionResource(id = R.dimen.paddingAll)),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.margintTopVerifyPhone)))


        VerifyHeader()

        TwoWrongVerification()

        WrongNumberChange()

        DigitCodes()

        Spacer(modifier = Modifier.height(
            dimensionResource(id = R.dimen.paddingLayouts30)))

        ResendCode()


        Spacer(modifier = Modifier.
            height(dimensionResource(id = R.dimen.paddingLayouts30)))

        ButtonSubmit(
            onClick = {onProceedClick()}, title =
            stringResource(id = R.string.proceed))


        TermsAndConditions()

        Spacer(modifier = Modifier.fillMaxHeight().weight(1.0f))
        VerifyBottomLine()

    }







}

