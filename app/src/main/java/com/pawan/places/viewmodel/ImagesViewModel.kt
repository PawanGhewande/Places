package com.pawan.places.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pawan.places.db.model.ImageData
import com.pawan.places.repository.ImagesRepository
import com.pawan.places.util.AppResult
import com.pawan.places.util.SingleLiveEvent
import kotlinx.coroutines.launch

class ImagesViewModel(private val repository: ImagesRepository) : ViewModel() {

    val showLoading = ObservableBoolean()
    val imagesList = MutableLiveData<List<ImageData>>()
    val showError = SingleLiveEvent<String?>()

    fun getAllImages() {
        showLoading.set(true)
        viewModelScope.launch {
            val result = repository.getAllImages()

            showLoading.set(false)
            when (result) {
                is AppResult.Success -> {
                    imagesList.value = result.successData!!
                    showError.value = null
                    Log.e("CALLED :: ",""+imagesList.value?.size)

                }
                is AppResult.Error -> showError.value = result.exception.message
            }
        }
    }
}