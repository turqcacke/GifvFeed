package com.app.gifvfeed.presentation.utils

import java.util.*
import java.util.concurrent.TimeUnit

object DateTimeFormatter {

    private const val DAYS = "д"
    private const val HOURS = "ч"
    private const val MINUTES = "м"
    private const val NOW = "сейчас"

    fun getDifference(pastDateSeconds: Long, nowDate: Long = System.currentTimeMillis()): String{
        val diff = Date(nowDate).time - Date(pastDateSeconds * 1000L).time
        return when{
            TimeUnit.MILLISECONDS.toDays(diff) > 0 -> "${TimeUnit.MILLISECONDS.toDays(diff)}$DAYS"
            TimeUnit.MILLISECONDS.toHours(diff) > 0 -> "${TimeUnit.MILLISECONDS.toHours(diff)}$HOURS"
            TimeUnit.MILLISECONDS.toMinutes(diff) > 0 -> "${TimeUnit.MILLISECONDS.toMinutes(diff)}$MINUTES"
            else -> NOW
        }
    }

}