package com.walletline.android.presentation.screens.auth.verify_email

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.walletline.android.R
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.screens.auth.components.AuthButton
import com.walletline.android.presentation.screens.auth.components.AuthCard
import com.walletline.android.presentation.screens.auth.components.AuthCardTitle
import com.walletline.android.presentation.screens.auth.components.WalletlineHeader
import com.walletline.android.presentation.screens.auth.verify_email.component.OtpTextField
import com.walletline.android.presentation.screens.auth.verify_email.component.ResendCodeSection
import com.walletline.android.presentation.screens.auth.verify_email.component.TermsAndConditionsSection
import com.walletline.android.presentation.screens.auth.verify_email.component.VerifyEmailBodyText
import com.walletline.android.presentation.screens.auth.verify_email.component.VerifyEmailTimer
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.padding
import com.walletline.presentation.screens.auth.verify_email.VerifyEmailState

@Composable
fun VerifyEmailContent(
    state: VerifyEmailState,
    onEvent: (VerifyEmailContract.Event) -> Unit,
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
                    text = stringResource(R.string.verify_email_address)
                )

                Spacer(modifier = Modifier.height(MaterialTheme.padding.smallLarge))

                VerifyEmailBodyText(
                    email = state.email,
                    onEmailClicked = { onEvent(VerifyEmailContract.Event.OnChangeEmailClicked) }
                )

                Spacer(modifier = Modifier.height(MaterialTheme.padding.smallLarge))

                OtpTextField(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.padding.smallLarge),
                    otp = state.otp,
                    onOtpChange = {
                        onEvent(VerifyEmailContract.Event.OTPChange(it))
                    }
                )

                Spacer(modifier = Modifier.height(MaterialTheme.padding.smallLarge))

                AnimatedVisibility(
                    visible = !state.isTimerActive,
                    enter = expandVertically(),
                    exit = shrinkVertically(),
                ) {
                    ResendCodeSection(
                        onResendClick = {
                            onEvent(VerifyEmailContract.Event.OnResendClicked)
                        }
                    )
                }

                AnimatedVisibility(
                    visible = state.isTimerActive,
                    enter = expandVertically(),
                    exit = shrinkVertically(),
                ) {
                    VerifyEmailTimer(
                        timeRemainInSeconds = {
                            state.timer
                        }
                    )
                }

                Spacer(modifier = Modifier.height(MaterialTheme.padding.smallLarge))

                AuthButton(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.padding.smallLarge),
                    onClick = {
                        onEvent(VerifyEmailContract.Event.OnContinueClicked)
                    },
                    enabled = !state.isLoading,
                    isLoading = state.isLoading,
                    text = stringResource(R.string.button_continue),
                )

                Spacer(modifier = Modifier.height(MaterialTheme.padding.medium))


            }

            TermsAndConditionsSection(
                modifier = Modifier
                    .padding(
                        top = MaterialTheme.padding.extraLarge,
                        bottom = MaterialTheme.padding.small,
                        start = MaterialTheme.padding.smallLarge,
                        end = MaterialTheme.padding.smallLarge
                    ),
                onTermsClicked = {
                    onEvent(VerifyEmailContract.Event.OnTermsClicked)
                },
                onPolicyClick = {
                    onEvent(VerifyEmailContract.Event.OnPolicyClicked)
                }
            )

        }
    }
}

@Preview
@Composable
fun VerifyNumberContentPreview(
    @PreviewParameter(VerifyEmailPreviewProvider::class)
    state: VerifyEmailState,
) {

    WalletLineTheme {
        VerifyEmailContent(
            state = state,
            onEvent = {

            }
        )
    }
}