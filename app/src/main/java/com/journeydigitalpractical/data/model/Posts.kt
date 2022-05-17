package com.journeydigitalpractical.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Posts(val userId : Int, val id : Int, val title : String, val body : String): Parcelable
