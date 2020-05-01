package com.electron.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.electron.database.entity.MyApps

@Dao
interface AppDao {

    @Insert
    fun insert(myApps: MyApps)

    @Query("DELETE FROM app_table")
    fun deleteAllApps()

    @Query("SELECT * FROM app_table")
    fun getAllApps(): LiveData<List<MyApps>>
}