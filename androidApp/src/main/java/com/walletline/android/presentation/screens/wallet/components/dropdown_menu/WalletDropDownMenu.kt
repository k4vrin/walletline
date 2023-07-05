package com.walletline.android.presentation.screens.wallet.components.dropdown_menu


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.walletline.android.R
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding

@Composable
fun WalletDropDownMenu(
    displayManu: MutableState<Boolean> = mutableStateOf(false),
    onEditWalletIconClicked: () -> Unit
) {
    DropdownMenu(
        expanded = displayManu.value,
        onDismissRequest = { displayManu.value = false },
        offset = DpOffset(
            x = (-80.dp),
            y = (-MaterialTheme.padding.small)
        ),
        modifier = Modifier
            .width(Dimen.DropDownMenuWidth)
            .clip(RoundedCornerShape(Dimen.WalletButtonCornerRadius))
            .background(MaterialTheme.customColor.neutrals.one)
            //.heightIn(min = 95.dp)
            .padding(
                start = MaterialTheme.padding.extraMedium,
                top = MaterialTheme.padding.smallLarge,
                end = MaterialTheme.padding.smallLarge,
                bottom = MaterialTheme.padding.smallLarge
            )
            /*.padding(
                vertical = MaterialTheme.padding.smallLarge,
                horizontal = MaterialTheme.padding.extraMedium
            )*/
            .shadow(
                elevation = MaterialTheme.padding.small,
                spotColor = Color(0xFFE9E9EB),
                ambientColor = Color(0xFFE9E9EB),
                shape = RoundedCornerShape(Dimen.WalletButtonCornerRadius)
            )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.medium, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            DropdownMenuItem(
                onClick = onEditWalletIconClicked, text = {
                    DropDownMenuItemRow(
                        textSrc = R.string.edit_wallet_menu,
                        iconSrc = R.drawable.ic_edit,
                        modifier = Modifier.background(MaterialTheme.customColor.neutrals.one)
                    )
                }, modifier = Modifier.background(MaterialTheme.customColor.neutrals.one)
            )
        }
    }
}
