package com.walletline.android.presentation.screens.wallet


import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.omercemcicekli.cardstack.CardStack
import com.walletline.android.presentation.screens.wallet.components.*
import com.walletline.android.presentation.screens.wallet.components.lines.LinesList
import com.walletline.android.presentation.screens.wallet.components.partners_view.PartnersView
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding
import com.walletline.presentation.screens.wallet.WalletState


@Composable
fun WalletContent(
    onCreateLineClicked: () -> Unit,
    onWalletChanged: (Int) -> Unit,
    state: WalletState,
    paddingValues: PaddingValues,
    scrollState: ScrollState,
    onCreateWalletClicked: () -> Unit,
    modifier: Modifier = Modifier

) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(
                MaterialTheme.customColor.neutrals.one
            )

        //.verticalScroll(state = scrollState, enabled = true)
    )
    {
        item {
            if (state.showEmptyWallet) {
                EmptyWallet(onCreateWalletClicked)
            } else {
                Box(
                    modifier = Modifier
                        .padding(
                            start = MaterialTheme.padding.medium,
                            end = MaterialTheme.padding.medium,
                            top = MaterialTheme.padding.medium
                        )
                        .background(color = Color.Transparent)
                ) {
                    CardStack(
                        cardContents =
                        listOf(
                            {
                                WalletAccountSection(state = state)
                            },
                            {
                                WalletAccountSection(
                                    state = state,
                                    backgroundColor = MaterialTheme.customColor.main.two,
                                )
                            },
                            {
                                WalletAccountSection(
                                    state = state,
                                    backgroundColor = MaterialTheme.customColor.main.three,
                                )
                            }),
                        cardShape = RoundedCornerShape(MaterialTheme.padding.medium),
                        cardBorder = null,
                        cardElevation = 8.dp,
                        paddingBetweenCards = 12.dp,
                        animationDuration = 250,
                        onCardClick = { cardIndex ->
                            //lineItemss.value = lines[cardIndex]
                            onWalletChanged(cardIndex)
                        }
                    )
                }
            }
        }
        item {
            LineSwitchRow(modifier = Modifier.padding(MaterialTheme.padding.medium))
            /*  PartnersView(
                partnersList = state.partnersList,
                onViewAllClicked = onViewAllPartnersClicked,
                modifier = Modifier.padding(
                    horizontal = MaterialTheme.padding.medium,
                ),
            )*/
        }

        item {
            LinesList(
                onCreateLineClicked = onCreateLineClicked,
                modifier = Modifier.padding(
                    horizontal = MaterialTheme.padding.medium,
                ),
                linesItems = state.linesList
            )
            Spacer(Modifier.padding(paddingValues.calculateBottomPadding()))

        }

    }
}


@Preview
@Composable
private fun WalletContentPreview() {
    WalletContent(
        onCreateLineClicked = { /*TODO*/ },
        onWalletChanged = {},
        state = WalletState(),
        paddingValues = PaddingValues(20.dp),
        scrollState = rememberScrollState(),
        onCreateWalletClicked = {}
    )
}