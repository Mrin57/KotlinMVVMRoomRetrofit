package com.electron.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.electron.database.entity.MyApps
import com.electron.network.model.ServiceData
import com.electron.repository.AppRepository
import com.electron.repository.MainActivityRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MainActivityRepository(application)
    private val appRepository: AppRepository = AppRepository(application)
    var allMyApps: LiveData<List<MyApps>> = appRepository.getAllApps()
    val showProgress: LiveData<Boolean>
    val serviceData: LiveData<ServiceData>

    init {
        this.showProgress = repository.showProgress
        this.serviceData = repository.serviceData

    }

    fun changeState() {
        repository.changeState()
    }

    fun makeApiCallToGetData() {
        repository.makeApiCallToGetData()
    }

    fun insert(app: MyApps) {
        appRepository.insert(app)
    }

    fun deleteAllApps() {
        appRepository.deleteAllApps()
    }

    fun getAllApps(): LiveData<List<MyApps>> {
        return allMyApps
    }
}