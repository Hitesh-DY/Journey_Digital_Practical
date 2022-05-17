package com.journeydigitalpractical.data.local

import android.content.Context
import androidx.room.Room

/**
 * DatabaseBuilder class used for create singleton instance of Room Database class
 * A singleton is a design pattern that restricts the instantiation of a class to only one instance
 */
object DatabaseBuilder {

    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "JourneyDigital"
        ).build()

}