package com.walletline.android.presentation.screens.auth.social_login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.walletline.android.R
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.screens.auth.components.AuthCard
import com.walletline.android.presentation.screens.auth.components.AuthCardArrowText
import com.walletline.android.presentation.screens.auth.components.AuthCardTitle
import com.walletline.android.presentation.screens.auth.components.DatariversTeamText
import com.walletline.android.presentation.screens.auth.components.OrDivider
import com.walletline.android.presentation.screens.auth.components.WalletlineHeader
import com.walletline.android.presentation.screens.auth.social_login.component.SocialSignInButton
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding
import com.walletline.android.presentation.util.ThemePreviews

@Composable
fun SocialLoginContent(
    isClickEnable: Boolean = true,
    onEmailClicked: () -> Unit,
    onGoogleClicked: () -> Unit,
    onFacebookClicked: () -> Unit,
    onAppleClicked: () -> Unit,
) {

    WalletLineBackground {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
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
                    text = stringResource(R.string.enter_by_socials)
                )

                Spacer(modifier = Modifier.height(MaterialTheme.padding.extraMedium))

                SocialSignInButton(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.padding.extraMedium),
                    enabled = isClickEnable,
                    icon = R.drawable.ic_google,
                    text = stringResource(R.string.google),
                    onClick = onGoogleClicked
                )

                Spacer(modifier = Modifier.height(MaterialTheme.padding.smallMedium))

                SocialSignInButton(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.padding.extraMedium),
                    enabled = isClickEnable,
                    icon = R.drawable.ic_facebook,
                    text = stringResource(R.string.facebook),
                    onClick = onFacebookClicked
                )

                Spacer(modifier = Modifier.height(MaterialTheme.padding.smallMedium))

                SocialSignInButton(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.padding.extraMedium),
                    icon = R.drawable.ic_apple,
                    enabled = isClickEnable,
                    text = stringResource(R.string.apple),
                    iconTint = MaterialTheme.customColor.neutrals.main,
                    backgroundColor = MaterialTheme.customColor.neutrals.six,
                    textColor = MaterialTheme.customColor.neutrals.main,
                    onClick = onAppleClicked
                )

                Spacer(modifier = Modifier.height(MaterialTheme.padding.extraMedium))

                OrDivider()

                Spacer(modifier = Modifier.height(MaterialTheme.padding.medium))

                AuthCardArrowText(
                    modifier = Modifier
                        .padding(bottom = MaterialTheme.padding.smallLarge)
                        .clickable(enabled = isClickEnable, onClick = onEmailClicked),
                    text = stringResource(R.string.enter_by_email)
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

        AnimatedVisibility(
            visible = !isClickEnable
        ) {
            Dialog(
                onDismissRequest = {}
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.customColor.neutrals.two,
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .padding(MaterialTheme.padding.extraMedium)
                ) {
                    CircularProgressIndicator(color = MaterialTheme.customColor.main.main)
                }
            }
        }

    }
}

@ThemePreviews
@Composable
private fun SocialContentPreview() {
    WalletLineTheme {
        SocialLoginContent(
            onEmailClicked = {},
            onGoogleClicked = {},
            onFacebookClicked = {},
            onAppleClicked = {},

        )
    }
}