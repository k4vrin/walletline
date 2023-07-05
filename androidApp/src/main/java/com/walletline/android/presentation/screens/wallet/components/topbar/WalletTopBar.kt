package com.walletline.android.presentation.screens.wallet.components.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.walletline.android.R
import com.walletline.android.presentation.screens.wallet.components.TopBarRow
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding


@Composable
fun WalletTopBar(
    onEditWalletClicked: () -> Unit = {},
    onBackButtonClicked: () -> Unit,
    text : Int,
    showMenu :Boolean = false
    ) {
    var displayMenu = remember { mutableStateOf(false) }

    TopBarRow(
        textSrc = text,
        showMenu = showMenu,
        displayMenu = displayMenu,
        onMenuIconClicked = { displayMenu.value = !displayMenu.value },
        onBackButtonClicked = onBackButtonClicked,
        OnEditWalletClicked = onEditWalletClicked,
        modifier = Modifier
            .background(color = MaterialTheme.customColor.neutrals.one)
            .padding(
                start = MaterialTheme.padding.medium,
                end = MaterialTheme.padding.medium,
                top = MaterialTheme.padding.large,
                bottom = MaterialTheme.padding.medium
            )

    )
}

@Preview(showBackground = true)
@Composable
private fun WalletTopBarPreview() {
    //WalletTopBar(){}
}

