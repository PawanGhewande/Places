package com.pawan.places

import com.pawan.places.db.model.ImageData
import retrofit2.Response
import retrofit2.http.GET

interface ImageApi {
    @GET("/v2/list?page=34&limit=10")
    suspend fun getAllCountries(): Response<List<ImageData>>
}