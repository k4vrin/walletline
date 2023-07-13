package com.walletline.android.presentation.screens.wallet.partner_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.walletline.android.R
import com.walletline.android.presentation.screens.wallet.components.SaveButton
import com.walletline.android.presentation.screens.wallet.components.TopBarRow
import com.walletline.android.presentation.screens.wallet.models.PartnerData
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding

@Composable
fun PartnersListContent(){
    val partnersItems = listOf(
        PartnerData(1, "", ""),
        PartnerData(2, "", ""),
        PartnerData(3, "", ""),
        PartnerData(4, "", ""),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.customColor.neutrals.main)
            .padding(
                start = MaterialTheme.padding.medium,
                end = MaterialTheme.padding.medium,
            )
    ) {
        TopBarRow(
            textSrc = R.string.partners_list,
            onBackButtonClicked = {},
            modifier = Modifier
                .padding(
                    top = MaterialTheme.padding.large,
                    bottom = MaterialTheme.padding.smallLarge
                )
        )
        PartnersList(partnersItems = partnersItems)

        Spacer(modifier = Modifier.weight(1f))

        SaveButton(
            text = R.string.apply,
            modifier = Modifier.padding(bottom = MaterialTheme.padding.smallLarge)
        ) {}
    }
}