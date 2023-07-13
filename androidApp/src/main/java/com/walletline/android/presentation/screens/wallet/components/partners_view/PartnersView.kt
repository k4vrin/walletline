package com.walletline.android.presentation.screens.wallet.components.partners_view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.walletline.android.presentation.theme.padding
import com.walletline.domain.model.PartnerData

@Composable
fun PartnersView(
    partnersList: List<PartnerData>,
    onViewAllClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {
        PartnersViewHeader(onViewAllClicked)
        PartnersViewList(
            partnerItems = partnersList,
            modifier = Modifier.padding(top = MaterialTheme.padding.smallMedium)
        )

    }
}