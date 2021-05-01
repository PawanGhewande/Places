package com.pawan.places.modules

import android.app.Application
import androidx.room.Room
import com.pawan.places.db.ImageDatabase
import com.pawan.places.db.ImagesDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): ImageDatabase {
        return Room.databaseBuilder(application, ImageDatabase::class.java, "images")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideImagesDao(database: ImageDatabase): ImagesDao {
        return  database.imagesDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideImagesDao(get()) }


}