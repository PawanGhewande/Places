package com.pawan.places.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pawan.places.db.model.ImageData

@Dao
interface ImagesDao {

    @Query("SELECT * FROM ImageData")
    fun findAll(): List<ImageData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(users: List<ImageData>)
}