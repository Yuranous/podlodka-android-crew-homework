package com.example.dota.extentions

import androidx.compose.ui.graphics.Color
import android.graphics.Color as AndroidColor


fun Color.Companion.parse(hex: String, alpha: Float = 1f): Color {
    val androidColorInt = AndroidColor.parseColor(hex)
    return Color(androidColorInt).copy(alpha = alpha)
}