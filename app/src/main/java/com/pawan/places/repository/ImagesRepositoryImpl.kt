package com.pawan.places.repository


import android.content.Context
import com.pawan.places.ImageApi
import com.pawan.places.db.ImagesDao
import com.pawan.places.db.model.ImageData
import com.pawan.places.util.AppResult
import com.pawan.places.util.NetworkManager.isOnline
import com.pawan.places.util.Utils.handleApiError
import com.pawan.places.util.Utils.handleSuccess
import com.pawan.places.util.noNetworkConnectivityError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImagesRepositoryImpl(
    private val api: ImageApi,
    private val context: Context,
    private val dao: ImagesDao
) :
    ImagesRepository {

    override suspend fun getAllImages(): AppResult<List<ImageData>> {
        if (isOnline(context)) {
            return try {
                val response = api.getAllCountries()
                if (response.isSuccessful) {
                    //save the data
                    response.body()?.let {
                        withContext(Dispatchers.IO) {
                            dao.add(it)
                            //Log.e("ADDED DATA IN DB:: ",""+it.toList())
                        }
                    }
                    handleSuccess(response)
                } else {
                    handleApiError(response)
                }
            } catch (e: Exception) {
                AppResult.Error(e)
            }
        } else {
            //check in db if the data exists
            val data = getCountriesDataFromCache()
            return if (data.isNotEmpty()) {
                //Log.d("@@ImagesNotInDB", "from db")
                AppResult.Success(data)
            } else
            //no network
                context.noNetworkConnectivityError()
        }
    }

    private suspend fun getCountriesDataFromCache(): List<ImageData> {
        return withContext(Dispatchers.IO) {
            dao.findAll()
        }
    }

}