package com.journeydigitalpractical.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * The table for comment & initialised the fields
 * @Parcelable is an interface that a class can implement to be passed within an Intent from an Activity to another one
 * @Entity  Marks a class as an entity. This class will have a mapping SQLite table in the database
 * @ColumnInfo Allows specific customization about the column associated with this field
 * @PrimaryKey  Marks a field in an Entity as the primary key. If you would like to define a composite primary key, Make sure it will be unique key as well
 */

@Entity
data class Comments(
    @ColumnInfo(name = "postId") val postId: Int,
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "body") val body: String
)
