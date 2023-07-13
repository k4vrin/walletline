package com.walletline.android.presentation.screens.wallet.partner_list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.walletline.android.presentation.screens.wallet.models.PartnerData
import com.walletline.android.presentation.theme.padding

@Composable
fun PartnersList(
    partnersItems: List<PartnerData>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(partnersItems.size) { partnerIndex ->
            PartnerItem(
                modifier = Modifier.padding(
                    top = MaterialTheme.padding.medium,
                ),
                partner = partnersItems[partnerIndex]
            )
        }
    }
}