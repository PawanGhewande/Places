package com.pawan.places.modules

import android.content.Context
import com.pawan.places.ImageApi
import com.pawan.places.db.ImagesDao
import com.pawan.places.repository.ImagesRepository
import com.pawan.places.repository.ImagesRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    fun provideCountryRepository(api: ImageApi, context: Context, dao : ImagesDao): ImagesRepository {
        return ImagesRepositoryImpl(api, context, dao)
    }
    single { provideCountryRepository(get(), androidContext(), get()) }

}