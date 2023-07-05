package com.walletline.android.presentation.screens.wallet.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.walletline.android.R
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.customColor

@Composable
fun WalletEyeIcon(modifier: Modifier = Modifier, onClick: () -> Unit){
    IconButton(
        onClick = { /*TODO*/ },
        modifier = modifier
            .size(Dimen.WalletIconBackSize)
            .clip(CircleShape)
            .background(MaterialTheme.customColor.neutrals.main.copy(0.05f))
            .border(
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.customColor.neutrals.main.copy(
                            0.08f
                        ), MaterialTheme.customColor.neutrals.main.copy(0.04f)
                    )
                ), width = 0.75.dp, shape = CircleShape
            )
            .clickable{onClick}
    ) {
        Icon(
            painter = painterResource(id = R.drawable.eye),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(Dimen.WalletEyeIconSize)
            ,
        )
    }
}
