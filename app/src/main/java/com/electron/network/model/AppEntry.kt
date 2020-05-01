package com.electron.network.model

import com.google.gson.annotations.SerializedName

data class AppEntry(
    @SerializedName("im:name") val imName: CommonData,
    @SerializedName("rights") val rights: CommonData,
    @SerializedName("im:image") val imImage: ArrayList<ImageData>,
    @SerializedName("im:price") val imPrice: PriceData,
    val category: CategoryData,
    @SerializedName("im:releaseDate") val imReleaseDate: ReleaseData
)