package com.moose.ecoba.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [], version = 1, exportSchema = false)
abstract class EcobaDatabase: RoomDatabase() {

    abstract fun getDao(): EcobaDao

    companion object{
        const val DB_NAME="ecoba"
    }
}