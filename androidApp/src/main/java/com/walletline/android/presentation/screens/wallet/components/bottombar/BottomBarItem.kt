package com.walletline.android.presentation.screens.wallet.components.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.ramcosta.composedestinations.navigation.navigate
import com.walletline.android.presentation.theme.Dimen
import com.walletline.android.presentation.theme.padding

@Composable
fun BottomBarItem(
    screen: BottomBarScreen,
    navController: NavHostController, selected: Boolean, backgroundColor: Color, contentColor: Color
) {

    Box(
        modifier = Modifier
            .height(60.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable(onClick = {
                navController.navigate(screen.direction) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }


            })
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(MaterialTheme.padding.small),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Absolute.spacedBy(MaterialTheme.padding.extraSmall)
        ) {
            Icon(
                modifier = Modifier.size(Dimen.DefaultWalletButtonSize),
                painter = painterResource(id = screen.icon),
                contentDescription = "icon",
                tint = contentColor
            )
            /* AnimatedVisibility(visible = true, modifier = Modifier.padding(4.dp)) {
                 Text(
                     text = screen.title,
                     color = contentColor
                 )
             }*/
            Text(
                text = stringResource(screen.title),
                color = contentColor,
                style = MaterialTheme.typography.bodySmall
            )

        }
    }
}