package com.walletline.android.presentation.screens.wallet.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.walletline.android.R
import com.walletline.android.presentation.screens.wallet.components.dropdown_menu.WalletDropDownMenu
import com.walletline.android.presentation.screens.wallet.components.topbar.WalletTopBarIcon
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding

@Composable
fun TopBarRow(
    textSrc: Int,
    displayMenu: MutableState<Boolean> = mutableStateOf(false),
    showMenu: Boolean = false,
    onMenuIconClicked: () -> Unit = {},
    onBackButtonClicked: () -> Unit,
    OnEditWalletClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                spotColor = MaterialTheme.customColor.neutrals.six,
                ambientColor = MaterialTheme.customColor.neutrals.six
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .height(Dimen.DefaultButtonHeight)
                .background(color = Color.Transparent)
        ) {
            WalletTopBarIcon(onClick = onBackButtonClicked, icon = R.drawable.ic_back)
            Text(
                text = stringResource(id = textSrc),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.customColor.neutrals.six
            )
            WalletTopBarIcon(
                onClick = onMenuIconClicked,
                icon = R.drawable.ic_more,
                enabled = showMenu,
                isMenuIcon = true
            )
            if (!showMenu) {
                Spacer(modifier = Modifier.size(Dimen.DefaultButtonHeight))
            }
        }
        WalletDropDownMenu(
            displayManu = displayMenu,
            onEditWalletIconClicked = OnEditWalletClicked
        )
    }
}
