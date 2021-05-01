package com.pawan.places.repository

import androidx.lifecycle.LiveData
import com.pawan.places.db.model.ImageData
import com.pawan.places.util.AppResult

interface ImagesRepository {
    suspend fun getAllImages() : AppResult<List<ImageData>>
}