package com.electron.database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.electron.database.dao.AppDao
import com.electron.database.entity.MyApps

@Database(entities = [MyApps::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "app_database"
                    )
                        .fallbackToDestructiveMigration()
                        //.addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance)
                    .execute()
            }
        }
    }

    class PopulateDbAsyncTask(db: AppDatabase?) : AsyncTask<Unit, Unit, Unit>() {
        private val noteDao = db?.appDao()

        override fun doInBackground(vararg p0: Unit?) {
            /*noteDao?.insert(Note("Title 1", "description 1"))
            noteDao?.insert(Note("Title 2", "description 2"))
            noteDao?.insert(Note("Title 3", "description 3"))*/
        }
    }
}