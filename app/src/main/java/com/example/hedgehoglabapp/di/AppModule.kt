package com.example.hedgehoglabapp.di

import com.example.hedgehoglabapp.local.HedgehogDao
import com.example.hedgehoglabapp.remote.NetworkApi
import com.example.hedgehoglabapp.repository.gallery.GalleryRepository
import com.example.hedgehoglabapp.repository.gallery.GalleryRepositoryImpl
import com.example.hedgehoglabapp.repository.details.DetailsRepository
import com.example.hedgehoglabapp.repository.details.DetailsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideGalleryRepository(api: NetworkApi): GalleryRepository =
        GalleryRepositoryImpl(api)


    @Provides
    fun provideDetailsRepository(hedgehogDao: HedgehogDao): DetailsRepository =
        DetailsRepositoryImpl(hedgehogDao)

}