package com.example.dota.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.dota.extentions.parse

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = FontFamily.Default,
    h1 = TextStyle(
        color = Color.White,
        fontSize = 48.sp
    ),
    h2 = TextStyle(
        color = Color.White,
        fontSize = 20.sp
    ),
    h3 = TextStyle(
        color = Color.White,
        fontSize = 16.sp
    ),
    body1 = TextStyle(
        color = Color.parse("#A8ADB7"),
        fontSize = 12.sp
    ),
    body2 = TextStyle(
        color = Color.parse("#41A0E7"),
        fontSize = 10.sp
    ),
    button = TextStyle(
        color = Color.parse("#050B18"),
        fontSize = 20.sp
    ),
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)