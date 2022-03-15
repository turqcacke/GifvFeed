package com.app.gifvfeed.presentation.utils

object NumberFormatter {

    private const val THOUSAND = "K"
    private const val MILLION = "M"

    fun format(number: Int): String {
        return when {
            number == 0 -> number.toString()
            number % 1000 == 0 -> "${number/1000}$THOUSAND"
            number % 1000000 == 0 -> "${number/1000000}$MILLION"
            else -> number.toString()
        }
    }
}