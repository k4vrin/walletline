package com.walletline.android.presentation.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Stable
data class WalletlineColors(
    val material: ColorScheme,
    val main: Shade,
    val success: Shade,
    val warning: Shade,
    val error: Shade,
    val information: Shade,
    val neutrals: Shade,
)

@Stable
data class Shade(
    val main: Color,
    val one: Color,
    val two: Color,
    val three: Color,
    val four: Color,
    val five: Color,
    val six: Color,
)

val mainMain = Color(0xFF4C36ED)
val main1 = Color(0xFFE9E6FD)
val main2 = Color(0xFFD2CDFB)
val main3 = Color(0xFF8E80F2)
val main4 = Color(0xFF2812C8)
val main5 = Color(0xFF180B75)
val main6 = Color(0xFF0B0533)

val successMain = Color(0xFF2DD67C)
val success1 = Color(0xFFE0F9EC)
val success2 = Color(0xFFC0FEDD)
val success3 = Color(0xFF68F2A8)
val success4 = Color(0xFF23A861)
val success5 = Color(0xFF146137)
val success6 = Color(0xFF092617)

val warningMain = Color(0xFFF7C71E)
val warning1 = Color(0xFFFEF8E3)
val warning2 = Color(0xFFFCEAAB)
val warning3 = Color(0xFFFAD964)
val warning4 = Color(0xFFB28C06)
val warning5 = Color(0xFF644f04)
val warning6 = Color(0xFF292001)

val errorMain = Color(0xFFE33030)
val error1 = Color(0xFFFBE5E5)
val error2 = Color(0xFFF4B1B1)
val error3 = Color(0xFFEA6E6E)
val error4 = Color(0xFFBA1919)
val error5 = Color(0xFF8C1414)
val error6 = Color(0xFF330707)

val infoMain = Color(0xFF2F69FD)
val info1 = Color(0xFFE5ECFF)
val info2 = Color(0xFFB1C7FE)
val info3 = Color(0xFF7097FE)
val info4 = Color(0xFF043DCF)
val info5 = Color(0xFF01206F)
val info6 = Color(0xFF001036)

val neutralsMain = Color(0xFFFFFFFF)
val neutrals1 = Color(0xFFF9F9F9)
val neutrals2 = Color(0xFFCACACA)
val neutrals3 = Color(0xFF96959E)
val neutrals4 = Color(0xFF4E4D59)
val neutrals5 = Color(0xFF2B2A38)
val neutrals6 = Color(0xFF000000)
