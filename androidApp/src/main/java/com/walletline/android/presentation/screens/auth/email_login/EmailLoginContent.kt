package com.walletline.android.presentation.screens.auth.email_login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import com.walletline.android.R
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.screens.auth.components.*
import com.walletline.android.presentation.screens.auth.email_login.component.EmailTextField
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.padding
import com.walletline.android.presentation.util.ThemePreviews

@Composable
fun EmailLoginContent(
    emailText: String,
    emailErrorMessage: String? = null,
    isClickEnable: Boolean = true,
    onEmailChange: (String) -> Unit,
    onSocialClicked: () -> Unit,
    onContinueClicked: () -> Unit,
) {

    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    WalletLineBackground {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) { focusManager.clearFocus() },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(Dimen.WalletlineLogoTopMargin))

            WalletlineHeader()

            Spacer(modifier = Modifier.height(Dimen.WalletlineLogoBottomMargin))

            AuthCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.padding.medium)
            ) {

                AuthCardTitle(
                    modifier = Modifier
                        .padding(top = MaterialTheme.padding.smallLarge),
                    text = stringResource(R.string.enter_by_email)
                )

                Spacer(modifier = Modifier.height(MaterialTheme.padding.extraMedium))

                EmailTextField(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.padding.extraMedium),
                    text = emailText,
                    onTextChange = onEmailChange,
                    errorMessage = emailErrorMessage
                )

                Spacer(modifier = Modifier.height(MaterialTheme.padding.extraMedium))

                AuthButton(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.padding.extraMedium),
                    enabled = isClickEnable,
                    text = "Continue",
                    onClick = onContinueClicked
                )

                Spacer(modifier = Modifier.height(MaterialTheme.padding.extraMedium))

                OrDivider()

                Spacer(modifier = Modifier.height(MaterialTheme.padding.medium))

                AuthCardArrowText(
                    modifier = Modifier
                        .padding(bottom = MaterialTheme.padding.smallLarge)
                        .clickable(enabled = isClickEnable, onClick = onSocialClicked),
                    text = stringResource(id = R.string.enter_by_socials)
                )
            }

            DatariversTeamText(
                modifier = Modifier
                    .padding(
                        top = MaterialTheme.padding.extraLarge,
                        bottom = MaterialTheme.padding.small
                    )
            )
        }
    }
}

@ThemePreviews
@Composable
fun MobileNumberPreviewTheme() {
    var text by remember { mutableStateOf("") }
    WalletLineTheme {
        EmailLoginContent(
            emailText = text,
            onEmailChange = { text = it },
            onContinueClicked = {},
            onSocialClicked = {}
        )
    }
}