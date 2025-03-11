package com.together.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val textGray = Color(0xFF333333)

data class CustomColors(
    val backgroundInputField: Color = Color(0x33333333),
    val backgroundYellow: Color = Color(0xFFFFD80C),
    val textDescription: Color = Color(0xFF646464),
    val backgroundGray: Color = Color(0xFFD7D7D7),
    val unselectedDot: Color = Color(0xFFD9D9D9),
    val cardTextDescription: Color = Color(0xFF806B00),
    val blackTextDescription: Color = Color(0xFF111111)
)

val LocalCustomColors = staticCompositionLocalOf { CustomColors() }