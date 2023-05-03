package com.walletline.android.presentation.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


private val WalletLineLightColorScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
    outlineVariant = md_theme_light_outlineVariant,
    scrim = md_theme_light_scrim,
)


private val WalletLineDarkColorScheme = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
)

private val WalletlineLiteCustomColor = WalletlineColors(
    material = WalletLineLightColorScheme,
    main = Shade(
        main = mainMain,
        one = main1,
        two = main2,
        three = main3,
        four = main4,
        five = main5,
        six = main6
    ),
    success = Shade(
        main = successMain,
        one = success1,
        two = success2,
        three = success3,
        four = success4,
        five = success5,
        six = success6
    ),
    warning = Shade(
        main = warningMain,
        one = warning1,
        two = warning2,
        three = warning3,
        four = warning4,
        five = warning5,
        six = warning6
    ),
    error = Shade(
        main = errorMain,
        one = error1,
        two = error2,
        three = error3,
        four = error4,
        five = error5,
        six = error6
    ),
    information = Shade(
        main = infoMain,
        one = info1,
        two = info2,
        three = info3,
        four = info4,
        five = info5,
        six = info6
    ),
    neutrals = Shade(
        main = neutralsMain,
        one = neutrals1,
        two = neutrals2,
        three = neutrals3,
        four = neutrals4,
        five = neutrals5,
        six = neutrals6
    )

)

val LocalCustomColor = staticCompositionLocalOf { WalletlineLiteCustomColor }
val MaterialTheme.customColor: WalletlineColors
    @Composable
    @ReadOnlyComposable
    get() = LocalCustomColor.current

@Composable
fun WalletLineTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    // Keep this value false in development
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> WalletLineDarkColorScheme
        else -> WalletLineLightColorScheme
    }


    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val activity = view.context as Activity
            activity.window.statusBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(activity.window, view).isAppearanceLightStatusBars =
                !darkTheme
        }
    }

    val customColor = when {
        darkTheme -> WalletlineLiteCustomColor // FIXME:
        else -> WalletlineLiteCustomColor
    }

    val padding = Padding

    CompositionLocalProvider(
        LocalPadding provides padding,
        LocalCustomColor provides customColor
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography(),
            content = content
        )
    }

}