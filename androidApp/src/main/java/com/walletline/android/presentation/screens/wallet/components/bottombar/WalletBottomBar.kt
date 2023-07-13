package com.walletline.android.presentation.screens.wallet.components.bottombar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.walletline.android.presentation.screens.NavGraphs
import com.walletline.android.presentation.screens.appCurrentDestinationAsState
import com.walletline.android.presentation.screens.destinations.TypedDestination
import com.walletline.android.presentation.screens.startAppDestination
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.customColor
import com.walletline.android.presentation.theme.padding

@Composable
fun WalletBottomBar(navController: NavHostController, modifier: Modifier = Modifier) {
    val screens = listOf(
        BottomBarScreen.Wallet,
        BottomBarScreen.Home,

        )



     val currentDestination: TypedDestination<*> = (navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination)

    Log.d("BottomBar",currentDestination.toString())

    Box(
        modifier = Modifier
            .padding(top = MaterialTheme.padding.extraSmall)
            .background(color = Color.Transparent),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(Dimen.BottomBarHeight)
                .clip(BottomShape(curveCircleRadius = 40f))
                .background(color = MaterialTheme.customColor.neutrals.one)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.8f),
                            Color.Black.copy(alpha = 0.5f)
                        )
                    )
                ),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //  val currentRoute = navController.isRouteOnBackStack(screens[0].direction)

            AddItem(
                screen = screens[0],
                currentDestination = currentDestination,
                navController = navController
            )
            FloatingActionButton(
                modifier = Modifier.size(Dimen.WalletFABSize),
                onClick = { },
                shape = CircleShape,
                containerColor = MaterialTheme.customColor.main.main,
                contentColor = Color.White,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Add, "Localized description")
            }
            AddItem(
                screen = screens[1],
                currentDestination = currentDestination,
                navController = navController
            )
        }

    }
}

@Preview
@Composable
private fun WalletBottomBarPreview() {
    WalletBottomBar(rememberNavController())
}




