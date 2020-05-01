package com.electron.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.electron.database.AppDatabase
import com.electron.database.dao.AppDao
import com.electron.database.entity.MyApps

class AppRepository(application: Application) {
    private var appDao: AppDao
    private var allMyApps: LiveData<List<MyApps>>

    init {
        val database: AppDatabase = AppDatabase.getInstance(application.applicationContext)!!
        appDao = database.appDao()
        allMyApps = appDao.getAllApps()
    }

    fun insert(app: MyApps) {
        val insertAppTask = InsertAppAsyncTask(appDao).execute(app)
    }

    fun deleteAllApps() {
        val deleteAllAppsTask = DeleteAllAppAsyncTask(appDao).execute()
    }

    fun getAllApps(): LiveData<List<MyApps>> {
        return allMyApps
    }

    private class InsertAppAsyncTask(appDao: AppDao) : AsyncTask<MyApps, Unit, Unit>() {
        val appDao = appDao

        override fun doInBackground(vararg p0: MyApps?) {
            appDao.insert(p0[0]!!)
        }
    }


    private class DeleteAllAppAsyncTask(val appDao: AppDao) : AsyncTask<Unit, Unit, Unit>() {

        override fun doInBackground(vararg p0: Unit?) {
            appDao.deleteAllApps()
        }
    }
}