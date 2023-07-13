package com.walletline.android.presentation.screens.wallet.components.partners_view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.walletline.android.presentation.screens.wallet.components.NewPartnerButton
import com.walletline.android.presentation.screens.wallet.components.PartnerItem
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding
import com.walletline.domain.model.PartnerData

@Composable
fun PartnersViewList(partnerItems: List<PartnerData>, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top
    ) {
        LazyRow() {
            items(partnerItems.size) { partnerIndex ->
                PartnerItem(
                    partner = partnerItems[partnerIndex],
                    modifier = Modifier.padding(end = MaterialTheme.padding.smallMedium)
                )
            }
            item(contentType = "footer") {
                Column(
                    modifier = Modifier
                        .padding(end = MaterialTheme.padding.medium),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.size(Dimen.DefaultButtonHeight)
                    ) {
                        Text(
                            text = "+2", style = MaterialTheme.typography.headlineSmall,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.customColor.neutrals.six,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    Text(
                        text = "More", style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.customColor.neutrals.six,
                        modifier = Modifier
                            .padding(top = MaterialTheme.padding.extraSmall)
                    )
                }
            }
        }
        NewPartnerButton {}
    }
}