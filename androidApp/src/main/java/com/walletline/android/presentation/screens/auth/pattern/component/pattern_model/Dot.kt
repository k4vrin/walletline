package com.walletline.android.presentation.screens.auth.pattern.component.pattern_model

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

data class Dot (
    val id: Int,
    val offset: Offset,
    val animatebleSize: Animatable<Float, AnimationVector1D>,
    var color: Color
)