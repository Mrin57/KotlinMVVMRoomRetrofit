package com.electron.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_table")
class MyApps {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "app_name")
    var appName: String = ""

    @ColumnInfo(name = "app_image")
    var appImageUrl: String = ""

    var category: String = ""

    var price: String = ""

    @ColumnInfo(name = "release_date")
    var releaseDate: String = ""

    constructor(
        appName: String,
        appImageUrl: String,
        category: String,
        price: String,
        releaseDate: String
    ) {
        this.appName = appName
        this.appImageUrl = appImageUrl
        this.category = category
        this.price = price
        this.releaseDate = releaseDate
    }
}