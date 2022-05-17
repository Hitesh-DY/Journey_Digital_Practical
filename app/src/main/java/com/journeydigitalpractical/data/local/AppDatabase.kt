package com.journeydigitalpractical.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.journeydigitalpractical.data.local.dao.PostsDao
import com.journeydigitalpractical.data.model.Comments
import com.journeydigitalpractical.data.model.Posts

@Database(entities = [Posts::class, Comments::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao

}