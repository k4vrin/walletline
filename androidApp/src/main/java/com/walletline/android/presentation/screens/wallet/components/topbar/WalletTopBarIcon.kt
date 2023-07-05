package com.walletline.android.presentation.screens.wallet.components.topbar

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.walletline.android.presentation.theme.Dimen

@Composable
fun WalletTopBarIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: Int,
    enabled: Boolean = false,
    isMenuIcon: Boolean = false
) {
    if ((isMenuIcon && enabled) || (!isMenuIcon && !enabled)) {
        IconButton(
            onClick = onClick,
            modifier = modifier
                .size(Dimen.DefaultButtonHeight)
                .clip(CircleShape)
                .border(
                    width = Dimen.DefaultButtonStrokeWidth,
                    color = Color(0xFFEFEFEF),
                    shape = CircleShape
                )
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = Color.Unspecified

            )
        }
    }
}