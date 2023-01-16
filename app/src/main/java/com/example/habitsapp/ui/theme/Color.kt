package com.example.habitsapp.ui.theme


import androidx.compose.material.Colors
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color



val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)




val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val yellow200 = Color(0xffffeb46)
val yellow400 = Color(0xffffc000)
val yellow500 = Color(0xffffde03)
val yellowDarkPrimary = Color(0xff242316)


val blue200 = Color(0xff91a4fc)
val blue700 = Color(0xff6ef8ff)
val blue800 = Color(0xff26eeff)
val blueDarkPrimary = Color(0xff1c1d24)



val Colors.bottomBarContainerColor
@Composable
get() = if(isLight) Color.Transparent else Color.Black


val Colors.selectedIndicatorColor
@Composable
get() = if(isLight) Color.Magenta else Color.LightGray



val Colors.textColor
    @Composable
    get() = if(isLight) Color.Black else Color.White