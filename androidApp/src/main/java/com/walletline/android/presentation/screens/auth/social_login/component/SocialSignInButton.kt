package com.walletline.android.presentation.screens.auth.social_login.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.R
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.WalletLineTheme
import com.walletline.android.presentation.theme.padding

@Composable
fun SocialSignInButton(
    @DrawableRes icon: Int,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    backgroundColor: Color = Color.Transparent,
    iconTint: Color? = null,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    onClick: () -> Unit
) {

    Surface(
        onClick = onClick,
        modifier = modifier
            .height(Dimen.SocialMediaButtonHeight),
        enabled = enabled,
        shape = RoundedCornerShape(Dimen.DefaultButtonCornerRadius),
        color = backgroundColor,
        contentColor = textColor,
        border = BorderStroke(
            width = Dimen.DefaultButtonStrokeWidth,
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f)
        ),
        interactionSource = remember { MutableInteractionSource() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                modifier = Modifier
                    .size(Dimen.SocialMediaIconSize),
                painter = painterResource(id = icon),
                contentDescription = text,
                colorFilter = if (iconTint != null) ColorFilter.tint(iconTint) else null
            )

            Spacer(modifier = Modifier.width(MaterialTheme.padding.smallMedium))

            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center
            )

        }
    }
}

@Preview
@Composable
fun SocialSignInPreview() {
    WalletLineTheme {
        WalletLineBackground {
            SocialSignInButton(
                icon = R.drawable.ic_apple,
                text = "Sign in with apple",
                backgroundColor = Color.White,
                onClick = {}
            )
        }
    }
}