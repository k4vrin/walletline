package com.walletline.android.presentation.screens.wallet.edit_wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.R
import com.walletline.android.presentation.screens.wallet.WalletContract
import com.walletline.android.presentation.screens.wallet.components.DashedBorderedButton
import com.walletline.android.presentation.screens.wallet.components.SaveButton
import com.walletline.android.presentation.screens.wallet.components.TopBarRow
import com.walletline.android.presentation.screens.wallet.components.topbar.WalletTopBar
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding
import com.walletline.presentation.screens.wallet.EditWalletState


@Composable
fun EditWalletContent(
    state: EditWalletState,
    onSaveButtonClicked: () -> Unit,
    onWalletTitleChanged: (String) -> Unit,
    onWalletBalanceChanged: (String) -> Unit,
    onWalletDescriptionChanged: (String) -> Unit,
    walletTitleErrorMessage: MutableState<String> = mutableStateOf(""),
    walletBalanceErrorMessage: MutableState<String> = mutableStateOf(""),
    onDeleteWalletClicked: (String) -> Unit,
    paddingValues: PaddingValues,

    ) {
    var buttonTextSrc = R.string.save
    if (!state.editWalletMode) {
        buttonTextSrc = R.string.apply
    }
    val buttonText = remember {
        mutableStateOf(buttonTextSrc)
    }


    Box(modifier = Modifier.padding(paddingValues)){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.customColor.neutrals.main)
                .padding(
                    start = MaterialTheme.padding.medium,
                    end = MaterialTheme.padding.medium,
                    top = MaterialTheme.padding.medium,
                )
        ) {
            /*TopBarRow(
                textSrc = topBarText.value,
                onBackButtonClicked = onBackButtonClicked,
                modifier = Modifier
                    .padding(
                        top = MaterialTheme.padding.large,
                        bottom = MaterialTheme.padding.smallLarge
                    ),
            )*/

            EditWalletColumn(
                value = state.walletTitle,
                titleTextSrc = R.string.title,
                leadingIcon = {
                    Box(
                        modifier = Modifier
                            .height(Dimen.PhoneTextFieldHeight)
                            .width(Dimen.PhoneTextFieldDividerMarginStart),
                        contentAlignment = Alignment.Center,
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.ic_title),
                            contentDescription = stringResource(R.string.desc_email_icon),
                            tint = Color.Unspecified

                        )

                    }
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
                    Box(
                        modifier = Modifier
                            .height(Dimen.PhoneTextFieldHeight)
                            .width(Dimen.PhoneTextFieldDividerMarginStart),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_dollar),
                            contentDescription = stringResource(R.string.desc_email_icon),
                            tint = Color.Unspecified
                        )

                    }
                },
                placeholderTextSrc = R.string.balance_placeholder,
                textFieldHeight = Dimen.TextFieldSmallHeight,
                onTextChanged = onWalletBalanceChanged,
                textFiledErrorMessage = walletBalanceErrorMessage

            )

            if (state.editWalletMode) {
                Spacer(modifier = Modifier.height(MaterialTheme.padding.extraMedium))
            } else {
                Spacer(modifier = Modifier.height(MaterialTheme.padding.smallLarge))
                DashedBorderedButton(R.string.set_lines) {}
            }

            EditWalletColumn(
                value = state.walletDescription,
                titleTextSrc = R.string.description,
                optionalTitleSrc = R.string.optional,
                placeholderTextSrc = R.string.describe_your_category,
                textFieldHeight = Dimen.TextFieldLargeHeight,
                onTextChanged = onWalletDescriptionChanged

            )

            Spacer(modifier = Modifier.height(MaterialTheme.padding.extraMedium))

            /*ShareButton(modifier = Modifier.padding(horizontal = MaterialTheme.padding.extraLarge)) {
            }*/
            Spacer(modifier = Modifier.height(MaterialTheme.padding.extraMedium))

            if (state.editWalletMode) {
                Text(
                    text = stringResource(R.string.delete_wallet),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.customColor.main.four,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterHorizontally)
                        .clickable { onDeleteWalletClicked(state.walletId) }
                )
            }
        }
        SaveButton(
            text = buttonText.value,
            modifier = Modifier
                .padding(
                    start = MaterialTheme.padding.medium,
                    end = MaterialTheme.padding.medium,
                   // bottom = MaterialTheme.padding.extraLarge
                )
                .align(
                    BottomCenter
                ),
            onClick = onSaveButtonClicked
        )

        Spacer(Modifier.padding(paddingValues.calculateBottomPadding()))
    }
}

@Preview
@Composable
private fun EditWalletContentPreview() {
    // EditWalletContent(EditWalletState()){}
}