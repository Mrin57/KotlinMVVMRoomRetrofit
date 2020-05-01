package com.electron.network

import com.electron.network.model.ServiceData
import retrofit2.Call
import retrofit2.http.GET

//todo addbase url
const val BASE_URL = "https://itunes.apple.com/us/rss/newfreeapplications/limit=2/"

interface ApiInterface {

    @GET("json")
    fun getAppData(): Call<ServiceData>
}