package com.walletline.android.presentation.screens.wallet.components.bottombar

import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.ramcosta.composedestinations.utils.isRouteOnBackStack
import com.walletline.android.presentation.screens.destinations.TypedDestination
import com.walletline.android.presentation.theme.customColor

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination:TypedDestination<*>,
    navController: NavHostController
) {
    //val selected = currentDestination?.hierarchy?.any { it.route == screen.direction } == true
    //val selected = currentDestination == screen.direction
    val selected = navController.isRouteOnBackStack(screen.direction)

    Log.d("BottomBarAdd",selected.toString())
    Log.d("BottomBarAdd", screen.direction.toString())
    val background = Color.Transparent

    val contentColor =
        if (selected) MaterialTheme.customColor.neutrals.main else MaterialTheme.customColor.neutrals.three

    BottomBarItem(
        screen = screen,
        navController = navController,
        selected = selected,
        backgroundColor = background,
        contentColor = contentColor
    )
}
