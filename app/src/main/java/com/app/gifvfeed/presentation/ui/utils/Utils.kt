package com.app.gifvfeed.presentation.ui.utils

import androidx.compose.ui.graphics.Color

object Utils {

    @JvmStatic
    fun parseColorFromString(colorString: String): Color{
        return Color(android.graphics.Color.parseColor("#$colorString"))
    }

}