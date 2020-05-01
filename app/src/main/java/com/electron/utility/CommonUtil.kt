package com.electron.utility

import android.content.Context
import android.net.ConnectivityManager
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.round

/**
 * Reduces size of price to single value after decimal
 */
fun roundToTwo(value: Float): String {
    val newValue = round((value * 100).toDouble()) / 100
    return newValue.toString()
}

/**
 * Checks internet connection
 */
val Context.isConnected: Boolean
    get() {
        return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .activeNetworkInfo?.isConnected == true
    }

/**
 * Changed the date format to d MMMM, yyyy
 */
fun changeDateFormat(inputDate: String): String {
    val inputDateFormatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    val outputDateFormatter = SimpleDateFormat("d MMMM, yyyy", Locale.getDefault())
    val date = inputDateFormatter.parse(inputDate)
    return outputDateFormatter.format(date)
}