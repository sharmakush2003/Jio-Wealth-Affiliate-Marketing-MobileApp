package com.example.zillion.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
  primary = ZillionGreen,
  secondary = ZillionActionGreen,
  background = ZillionDark,
  surface = Color(0xFF1E2220),
  onPrimary = ZillionWhite,
  onSecondary = ZillionWhite,
  onBackground = ZillionLightGray,
  onSurface = ZillionLightGray
)

private val LightColorScheme = lightColorScheme(
  primary = ZillionGreen,
  secondary = ZillionActionGreen,
  background = ZillionLightGray,
  surface = ZillionWhite,
  onPrimary = ZillionWhite,
  onSecondary = ZillionWhite,
  onBackground = ZillionDark,
  onSurface = ZillionDark
)

@Composable
fun ZillionTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
