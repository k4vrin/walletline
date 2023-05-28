package com.walletline.android.presentation.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel

fun AnnotatedString.Builder.appendSpace() = this.append(" ")


@SuppressLint("ComposableNaming")
@Composable
fun <T> Flow<T>.collectInLaunchedEffect(function: suspend (value: T) -> Unit) {
    val flow = this
    LaunchedEffect(flow) {
        flow.collect(function)
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.koinSharedViewModel(navController: NavController) : T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(viewModelStoreOwner = parentEntry)
}