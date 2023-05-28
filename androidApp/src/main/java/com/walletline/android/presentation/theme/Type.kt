package com.walletline.android.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.walletline.android.R
import com.walletline.android.presentation.util.ssp

val plusJakarta = FontFamily(
    Font(R.font.plus_jakarta_sans_bold, weight = FontWeight.Bold),
    Font(R.font.plus_jakarta_sans_normal, weight = FontWeight.Normal),
    Font(R.font.plus_jakarta_sans_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(R.font.plus_jakarta_sans_medium, weight = FontWeight.Medium, style = FontStyle.Normal),
    Font(R.font.plus_jakarta_sans_semi_bold, weight = FontWeight.SemiBold),
)

@Composable
fun typography(): Typography =
     Typography(
         displaySmall = TextStyle(
             fontFamily = plusJakarta,
             fontWeight = FontWeight.Medium,
             fontSize = 24.ssp,
             lineHeight = 28.ssp,
             fontStyle = FontStyle.Italic
         ),
         headlineLarge = TextStyle(
             fontFamily = plusJakarta,
             fontWeight = FontWeight.SemiBold,
             fontSize = 20.ssp,
             lineHeight = 26.ssp,
         ),
         headlineMedium = TextStyle(
             fontFamily = plusJakarta,
             fontWeight = FontWeight.Medium,
             fontSize = 18.ssp,
             lineHeight = 21.ssp,
         ),
         headlineSmall = TextStyle(
             fontFamily = plusJakarta,
             fontWeight = FontWeight.Medium,
             fontSize = 16.ssp,
             lineHeight = 17.ssp,
         ),
        titleLarge = TextStyle(
            fontFamily = plusJakarta,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.ssp,
            lineHeight = 21.ssp,
        ),
         titleMedium = TextStyle(
             fontFamily = plusJakarta,
             fontWeight = FontWeight.Normal,
             fontSize = 16.ssp,
             lineHeight = 21.ssp,
         ),
        titleSmall = TextStyle(
            fontFamily = plusJakarta,
            fontWeight = FontWeight.Medium,
            fontSize = 12.ssp,
            lineHeight = 17.ssp,
            fontStyle = FontStyle.Italic
        ),
         bodyLarge = TextStyle(
             fontFamily = plusJakarta,
             fontWeight = FontWeight.Medium,
             fontSize = 14.ssp,
             lineHeight = 21.ssp,
         ),
         bodyMedium = TextStyle(
             fontFamily = plusJakarta,
             fontWeight = FontWeight.Normal,
             fontSize = 14.ssp,
             lineHeight = 17.ssp,
         ),
        bodySmall = TextStyle(
            fontFamily = plusJakarta,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.ssp,
            lineHeight = 25.ssp,
        ),
         // Label large is figma button style
         labelLarge = TextStyle(
             fontFamily = plusJakarta,
             fontWeight = FontWeight.SemiBold,
             fontSize = 14.ssp,
             lineHeight = 26.ssp,
         ),
    )



