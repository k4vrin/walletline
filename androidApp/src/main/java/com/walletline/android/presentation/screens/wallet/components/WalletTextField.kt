package com.walletline.android.presentation.screens.wallet.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
 fun WalletTextField(
    value :String,
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholder : (@Composable () -> Unit)
) {
    //val state = savedInstanceState(saver = TextFieldValue.Saver) { TextFieldValue() }

    val value = ""

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        if (leadingIcon != null) {
            leadingIcon()
        }
        Box(
            modifier = Modifier.weight(1f)
        ) {
            TextField(
                value = value,
                onValueChange = {  },
                placeholder = placeholder
            )
        }
        if (trailingIcon != null) {
            trailingIcon()
        }
    }
}
