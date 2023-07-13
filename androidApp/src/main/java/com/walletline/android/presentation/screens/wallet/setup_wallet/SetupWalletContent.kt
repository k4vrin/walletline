package com.walletline.android.presentation.screens.wallet.setup_wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.R
import com.walletline.android.presentation.screens.wallet.components.DashedBorderedButton
import com.walletline.android.presentation.screens.wallet.components.SaveButton
import com.walletline.android.presentation.screens.wallet.components.TopBarRow
import com.walletline.android.presentation.screens.wallet.edit_wallet.EditWalletColumn
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding
import com.walletline.presentation.screens.wallet.SetupWalletState

@Composable
fun SetupWalletContent(
    state:SetupWalletState,
    onWalletTitleChanged: (String) -> Unit,
    onWalletBalanceChanged: (String) -> Unit,
    onWalletDescriptionChanged: (String) -> Unit,
    walletTitleErrorMessage: MutableState<String> = mutableStateOf(""),
    walletBalanceErrorMessage: MutableState<String> = mutableStateOf("")
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.customColor.neutrals.main)
            .padding(
                start = MaterialTheme.padding.medium,
                end = MaterialTheme.padding.medium,
            )
    ) {
        TopBarRow(
            textSrc = R.string.setup_wallet,
            onBackButtonClicked = {},
            modifier = Modifier
                .padding(
                    top = MaterialTheme.padding.large,
                    bottom = MaterialTheme.padding.smallLarge
                )
        )
        EditWalletColumn(
            value = state.walletTitle,
            titleTextSrc = R.string.title,
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_title),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            },
            placeholderTextSrc = R.string.salary,
            textFieldHeight = Dimen.TextFieldSmallHeight,
            onTextChanged = onWalletTitleChanged,
            textFiledErrorMessage = walletTitleErrorMessage
        )
        Spacer(modifier = Modifier.height(MaterialTheme.padding.extraMedium))

        EditWalletColumn(
            value = state.walletBalance,
            titleTextSrc = R.string.balance,
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_dollar),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            },
            placeholderTextSrc = R.string.balance_placeholder,
            textFieldHeight = Dimen.TextFieldSmallHeight,
            onTextChanged = onWalletBalanceChanged,
            textFiledErrorMessage = walletBalanceErrorMessage
        )

        Spacer(modifier = Modifier.height(MaterialTheme.padding.smallLarge))

        DashedBorderedButton(R.string.create_first_wallet) {}

        Spacer(modifier = Modifier.height(MaterialTheme.padding.extraMedium))

        EditWalletColumn(
            value = state.walletDescription,
            titleTextSrc = R.string.description,
            placeholderTextSrc = R.string.describe_your_category,
            textFieldHeight = Dimen.TextFieldLargeHeight,
            onTextChanged = onWalletDescriptionChanged,

        )

        Spacer(modifier = Modifier.weight(1f))

        SaveButton(
            text = R.string.apply,
            modifier = Modifier.padding(bottom = MaterialTheme.padding.smallLarge)
        ) {}

    }


}

@Preview
@Composable
private fun EditWalletContentPreview() {
    // SetupWalletContent()
}