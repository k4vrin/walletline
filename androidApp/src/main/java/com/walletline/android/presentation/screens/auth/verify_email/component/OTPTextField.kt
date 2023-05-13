package com.walletline.android.presentation.screens.auth.verify_email.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding
import com.walletline.android.presentation.util.Constants
import com.walletline.android.presentation.util.sdp

@Composable
fun OtpTextField(
    otp: String,
    onOtpChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    borderWidth: Dp = 2.sdp,
    focusedBorderColor: Color = MaterialTheme.customColor.main.four,
    filledBorderColor: Color = MaterialTheme.customColor.main.three,
    unFocusedBorderColor: Color = MaterialTheme.customColor.neutrals.three,
) {

    BasicTextField(
        modifier = modifier,
        value = otp,
        onValueChange = {
            onOtpChange(it.take(Constants.OTPSize))
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.padding.smallMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(Constants.OTPSize) { index ->
                val char = when {
                    index >= otp.length -> ""
                    else -> otp[index].toString()
                }
                val isFocused = otp.length == index

                Box(
                    modifier = Modifier
                        .width(Dimen.OTPWidth)
                        .height(Dimen.OTPHeight)
                        .border(
                            border = BorderStroke(
                                width = borderWidth,
                                color = when {
                                    isFocused -> focusedBorderColor
                                    otp.getOrNull(index) != null -> filledBorderColor
                                    else -> unFocusedBorderColor
                                }
                            ),
                            shape = RoundedCornerShape(size = Dimen.OTPCornerRadius)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = char,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.customColor.neutrals.six,
                        textAlign = TextAlign.Center,
                    )
                }


            }
        }
    }

}

@Preview
@Composable
fun OtpTextFieldPreview() {
    var otp by remember {
        mutableStateOf("")
    }
    WalletLineTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.customColor.neutrals.main),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OtpTextField(
                modifier = Modifier,
                otp = otp,
                onOtpChange = { otp = it }
            )
        }
    }
}