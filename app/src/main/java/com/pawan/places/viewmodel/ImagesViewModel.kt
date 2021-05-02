package com.pawan.places.viewmodel

import android.content.Context
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatDelegate
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
                }
                is AppResult.Error -> showError.value = result.exception.message
            }
        }
    }

    fun changeTheme(context: Context?){
        if( MyPreferences(context).darkMode==0){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            MyPreferences(context).darkMode = 1
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            MyPreferences(context).darkMode = 0
        }
    }

}
class MyPreferences(context: Context?) {

    companion object {
        private const val DARK_STATUS = "pawan.app.DARK_STATUS"
    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    var darkMode = preferences.getInt(DARK_STATUS, 0)
        set(value) = preferences.edit().putInt(DARK_STATUS, value).apply()

}