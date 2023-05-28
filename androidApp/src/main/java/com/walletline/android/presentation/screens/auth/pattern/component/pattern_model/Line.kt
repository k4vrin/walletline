package com.walletline.android.presentation.screens.auth.pattern.component.pattern_model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.isSpecified

data class Line(
    val start: Offset,
    val end: Offset
) {
    fun isSpecified() = this.start.isSpecified && this.end.isSpecified
}