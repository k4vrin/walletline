package com.walletline.android.presentation.screens.wallet.partner_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.walletline.android.R
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.screens.wallet.models.PartnerData
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding

@Composable
fun PartnerItem(partner: PartnerData, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.height(Dimen.PartnerItemHeight),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(id = R.drawable.profile_image),
            contentDescription = null,
            modifier = Modifier
                .size(Dimen.DefaultButtonHeight)
                .clip(
                    CircleShape
                )
        )
        Text(
            "Sarah", style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.customColor.neutrals.six,
            modifier = Modifier.padding(start = MaterialTheme.padding.smallMedium)
        )
    }

}


@Preview
@Composable
fun PartnerItemPreview() {
    WalletLineBackground {
        PartnerItem(PartnerData(0, "", ""))
    }
}