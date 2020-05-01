package com.electron.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.electron.network.ApiInterface
import com.electron.network.BASE_URL
import com.electron.network.model.ServiceData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityRepository(val application: Application) {

    val showProgress = MutableLiveData<Boolean>()
    val serviceData = MutableLiveData<ServiceData>()

    fun changeState() {
        showProgress.value = !(showProgress.value != null && showProgress.value!!)
    }

    fun makeApiCallToGetData() {
        showProgress.value = true
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(ApiInterface::class.java)
        service.getAppData().enqueue(object : Callback<ServiceData> {
            override fun onFailure(call: Call<ServiceData>, t: Throwable) {
                showProgress.value = false
                Toast.makeText(application, "Unable to get data from api", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(call: Call<ServiceData>, response: Response<ServiceData>) {
                showProgress.value = false;
                //Log.d("ServiceResponse", "${Gson().toJson(response.body())}")
                serviceData.value = response.body()
            }

        })
    }
}