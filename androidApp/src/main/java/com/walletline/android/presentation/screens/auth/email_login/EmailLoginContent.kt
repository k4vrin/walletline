package com.walletline.android.presentation.screens.auth.email_login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import com.walletline.android.R
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.screens.auth.components.AuthButton
import com.walletline.android.presentation.screens.auth.components.AuthCard
import com.walletline.android.presentation.screens.auth.components.AuthCardArrowText
import com.walletline.android.presentation.screens.auth.components.AuthCardTitle
import com.walletline.android.presentation.screens.auth.components.DatariversTeamText
import com.walletline.android.presentation.screens.auth.components.OrDivider
import com.walletline.android.presentation.screens.auth.components.WalletlineHeader
import com.walletline.android.presentation.screens.auth.email_login.component.EmailTextField
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.padding
import com.walletline.android.presentation.util.ThemePreviews
import com.walletline.domain.model.EmailValidationMessage
import com.walletline.presentation.screens.auth.email_login.EmailLoginState

@Composable
fun EmailLoginContent(
    state: EmailLoginState,
    onEvent: (EmailLoginContract.Event) -> Unit,
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
                    text = state.email,
                    onTextChange = { onEvent(EmailLoginContract.Event.EmailChange(it)) },
                    errorMessage = when (state.emailError) {
                        is EmailValidationMessage.Dynamic -> (state.emailError as EmailValidationMessage.Dynamic).message
                        EmailValidationMessage.NotEmpty -> stringResource(id = R.string.err_email_not_empty)
                        EmailValidationMessage.NotValid -> stringResource(id = R.string.err_email_not_valid)
                        null -> null
                    }
                )

                Spacer(modifier = Modifier.height(MaterialTheme.padding.extraMedium))

                AuthButton(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.padding.extraMedium),
                    enabled = state.isActionsEnabled,
                    text = "Continue",
                    isLoading = state.isLoading,
                    onClick = { onEvent(EmailLoginContract.Event.OnContinueClicked) }
                )

                Spacer(modifier = Modifier.height(MaterialTheme.padding.extraMedium))

                OrDivider()

                Spacer(modifier = Modifier.height(MaterialTheme.padding.medium))

                AuthCardArrowText(
                    modifier = Modifier
                        .padding(bottom = MaterialTheme.padding.smallLarge)
                        .clickable(
                            enabled = state.isActionsEnabled,
                            onClick = { onEvent(EmailLoginContract.Event.OnEnterBySocialClicked) }),
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
            EmailLoginState()
        ) {

        }
    }
}