package com.l3on1kl.rick_and_morty.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private val DarkColorScheme = darkColorScheme(
    primary = PortalGreen,
    onPrimary = Color.Black,
    secondary = MortyYellow,
    onSecondary = Color.Black,
    tertiary = PlumbusPink,
    onTertiary = Color.Black,
    background = BackgroundDark,
    onBackground = TextPrimary,
    surface = SurfaceDark,
    onSurface = TextSecondary,
    error = StatusDead,
    onError = Color.Black,
    surfaceVariant = Color(0xFF2A2E39),
    onSurfaceVariant = Color(0xFFB0BEC5),
    outline = StatusUnknown,
    inverseOnSurface = Color.White,
    inversePrimary = ToxicGreen,
    scrim = Color.Black.copy(alpha = 0.6f)
)

private val LightColorScheme = lightColorScheme(
    primary = RickPurple,
    onPrimary = Color.White,
    secondary = MortyYellow,
    onSecondary = Color.Black,
    tertiary = PlumbusPink,
    onTertiary = Color.White,
    background = BackgroundLight,
    onBackground = TextOnLight,
    surface = SurfaceLight,
    onSurface = TextOnLightSecondary,
    error = StatusDead,
    onError = Color.White,
    surfaceVariant = Color(0xFFF2F2F2),
    onSurfaceVariant = Color(0xFF666666),
    outline = StatusUnknown,
    inverseOnSurface = Color.Black,
    inversePrimary = ToxicGreen,
    scrim = Color.Black.copy(alpha = 0.2f)
)

val Shapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp)
)

@Composable
fun RickAndMortyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
