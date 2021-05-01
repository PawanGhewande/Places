package com.pawan.places.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "ImageData")
@Parcelize
class ImageData (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val author: String?,
    val width: Int,
    val height: Int,
    val url: String?,
    val download_url: String?
) : Parcelable
