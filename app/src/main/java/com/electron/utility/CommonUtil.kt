package com.electron.utility

import android.content.Context
import android.net.ConnectivityManager
import kotlin.math.round

fun roundToTwo(value: Float): String {
    val newValue = round((value * 100).toDouble()) / 100
    return newValue.toString()
}

val Context.isConnected: Boolean
    get() {
        return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .activeNetworkInfo?.isConnected == true
    }