package com.pawan.places.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pawan.places.db.converter.Converters
import com.pawan.places.db.model.ImageData

@Database(
    entities = [ImageData::class],
    version = 1, exportSchema = false
)

@TypeConverters(Converters::class)
abstract class ImageDatabase : RoomDatabase() {
    abstract val imagesDao: ImagesDao
}