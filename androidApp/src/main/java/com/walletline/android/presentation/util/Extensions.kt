package com.walletline.android.presentation.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.AnnotatedString
import kotlinx.coroutines.flow.Flow

fun AnnotatedString.Builder.appendSpace() = this.append(" ")


@SuppressLint("ComposableNaming")
@Composable
fun <T> Flow<T>.collectInLaunchedEffect(function: suspend (value: T) -> Unit) {
    val flow = this
    LaunchedEffect(flow) {
        flow.collect(function)
    }
}