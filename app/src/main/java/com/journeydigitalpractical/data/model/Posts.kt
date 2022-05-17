package com.journeydigitalpractical.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * The table for posts & initialised the fields
 * @Parcelable is an interface that a class can implement to be passed within an Intent from an Activity to another one
 * @Entity  Marks a class as an entity. This class will have a mapping SQLite table in the database
 * @ColumnInfo Allows specific customization about the column associated with this field
 * @PrimaryKey  Marks a field in an Entity as the primary key. If you would like to define a composite primary key, Make sure it will be unique key as well
 */

@Parcelize
@Entity
data class Posts(
    @ColumnInfo(name = "userId") val userId : Int,
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "title") val title : String,
    @ColumnInfo(name = "body") val body : String): Parcelable
