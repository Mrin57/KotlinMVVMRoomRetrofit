package com.electron.network.model

import com.google.gson.annotations.SerializedName

data class AttributeData(
    val height: Int, val href: String, val rel: String, val type: String,
    @SerializedName("im:id") val imId: String,
    @SerializedName("im:bundleId") val imBundleId: String,
    val term: String, val amount: Float
)
