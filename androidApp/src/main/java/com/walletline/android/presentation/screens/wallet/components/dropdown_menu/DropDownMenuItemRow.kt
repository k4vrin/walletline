package com.walletline.android.presentation.screens.wallet.components.dropdown_menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.walletline.android.presentation.theme.padding

@Composable
fun DropDownMenuItemRow(textSrc: Int, iconSrc: Int, modifier: Modifier = Modifier) {
    Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
       // Spacer(modifier = Modifier.weight(1f))
        Icon(painter = painterResource(iconSrc), contentDescription = null)
        Text(
            text = stringResource(id = textSrc),
            modifier = Modifier.padding(
                start = MaterialTheme.padding.smallMedium,
                //end = MaterialTheme.padding.large
            )
        )

    }
}