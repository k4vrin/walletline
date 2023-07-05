package com.walletline.android.presentation.screens.wallet.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.walletline.android.R
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding

@Composable
fun WalletAccountHeader(eyeIconClicked : ()-> Unit, accountTitle:String){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_user_octagon),
            contentDescription = null,
            tint = Color.Unspecified,
        )

        Text(
            accountTitle,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.customColor.neutrals.main,
            modifier = Modifier.padding(start = MaterialTheme.padding.smallMedium)
        )

        Spacer(modifier = Modifier.weight(1f))

        WalletEyeIcon(onClick = eyeIconClicked)

    }
}