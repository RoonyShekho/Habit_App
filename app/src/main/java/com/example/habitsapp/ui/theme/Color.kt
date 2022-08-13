package com.example.habitsapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.ContentAlpha
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White


val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)




val Colors.textColor
@Composable
get() = if(isLight) Color.Black else White


val Colors.itemBackgroundColor
@Composable
get() = if(isLight) Purple200.copy(alpha = 0.077f) else Gray.copy(alpha = 0.1f)

val Colors.backgroundColor
@Composable
get() = if(isLight) Purple200.copy(alpha = 0.07f) else Color.Black