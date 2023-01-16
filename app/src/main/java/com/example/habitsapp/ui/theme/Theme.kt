package com.example.habitsapp.ui.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color


@Composable
fun TodoAppTheme(
    darkTheme: Boolean,
    colors: ColorScheme,
    content: @Composable () -> Unit
) {
    /*  val elevation = if (darkTheme) DarkElevation else LightElevation*/
/*    val images = if (darkTheme) DarkImages else LightImages*/
    /* val LocalElevations*/
    CompositionLocalProvider(
        /*     LocalElevations provides elevation,
             LocalImages provides images*/
    ) {
        MaterialTheme(
            colorScheme = colors,
            typography = typography,
            content = content
        )
    }
}




@Composable
fun BlueTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) {
        BlueThemeDark
    }else{
        BlueThemeLight
    }

    TodoAppTheme(darkTheme = darkTheme, colors, content)
}




private val BlueThemeDark = darkColorScheme(
    primary = blue200,
    secondary = yellow200,
    surface = blueDarkPrimary
)

private val BlueThemeLight = lightColorScheme(
    primary = blue700,
    onPrimary = Color.White,
    primaryContainer = blue800,
    secondary = yellow500
)