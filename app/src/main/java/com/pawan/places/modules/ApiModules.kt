package com.pawan.places.modules

import com.pawan.places.ImageApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideCountriesApi(retrofit: Retrofit): ImageApi {
        return retrofit.create(ImageApi::class.java)
    }
    single { provideCountriesApi(get()) }

}