package com.walletline.android.presentation.screens.wallet.components.partners_view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.walletline.android.R
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding

@Composable
fun PartnersViewHeader(onViewAllClicked: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = stringResource(id = R.string.partners),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.customColor.neutrals.six,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(id = R.string.view_all),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.customColor.main.four,
        )
        IconButton(onClick = onViewAllClicked,
            modifier = Modifier
                .size(Dimen.ViewAllArrowIconSize)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_right), contentDescription = null,
                tint = MaterialTheme.customColor.main.four,
                modifier = Modifier
                    .size(Dimen.ViewAllArrowIconSize)
                    .padding(start = MaterialTheme.padding.extraSmall)
            )
        }


    }
}