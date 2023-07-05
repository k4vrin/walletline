package com.walletline.android.presentation.screens.wallet.edit_wallet


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.walletline.android.R
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding

@Composable
fun EditWalletColumn(
    value: String,
    titleTextSrc: Int,
    optionalTitleSrc: Int = 0,
    leadingIcon: (@Composable () -> Unit) = {},
    placeholderTextSrc: Int,
    textFieldHeight: Dp,
    onTextChanged:(String)-> Unit,
    textFiledErrorMessage: MutableState<String> = mutableStateOf("")

) {
    Row(verticalAlignment = Alignment.Bottom){
        Text(
            text = stringResource(id = titleTextSrc),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.customColor.neutrals.six
        )
        if (optionalTitleSrc != 0) {
            Text(
                text = stringResource(id = optionalTitleSrc),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.customColor.neutrals.three,
                modifier = Modifier.padding(start = MaterialTheme.padding.extraSmall )
            )
        }
    }
    if (optionalTitleSrc == 0 || (placeholderTextSrc != R.string.describe_your_category)) {
        EditWalletTextField(
            modifier = Modifier
                .padding(top = MaterialTheme.padding.small),
            text = value,
            onTextChange = onTextChanged,
            leadingIcon = leadingIcon,
            placeholderTextSrc = placeholderTextSrc,
            errorMessage = textFiledErrorMessage.value
        )
    } else {
        EditWalletDescriptionTextField(
            modifier = Modifier
                .padding(top = MaterialTheme.padding.small),
            text = value,
            onTextChange = onTextChanged,
            placeholderTextSrc = placeholderTextSrc,
            errorMessage = textFiledErrorMessage.value
        )
    }
}