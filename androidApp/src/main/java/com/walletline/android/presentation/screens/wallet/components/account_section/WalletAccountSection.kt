package com.walletline.android.presentation.screens.wallet.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.walletline.android.presentation.components.WalletLineBackground
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding
import com.walletline.presentation.screens.wallet.WalletState

@Composable
fun WalletAccountSection(
    state: WalletState,
    backgroundColor: Color = MaterialTheme.customColor.main.main,
    modifier: Modifier = Modifier
) {
    Box(){
        WalletLineBackground(
            backgroundColor = backgroundColor,
            modifier = modifier
                .height(Dimen.WalletAccountSectionHeight)
                .fillMaxWidth()
                .clip(RoundedCornerShape(MaterialTheme.padding.medium)),
            ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = MaterialTheme.padding.extraMedium,
                        vertical = MaterialTheme.padding.extraMedium
                    ),
            ) {
                WalletAccountHeader(eyeIconClicked = {}, state.accountTitle)

                Spacer(modifier = Modifier.height(MaterialTheme.padding.smallLarge))

                WalletAccountDetailTitle()

                WalletAccountDetailContent(
                    state = state,
                    modifier = Modifier
                        .padding(top = MaterialTheme.padding.extraSmall)
                )

            }
        }
    }
}


@Composable
@Preview
private fun WalletAccountSectionPreview() {
    //WalletAccountSection()
}

