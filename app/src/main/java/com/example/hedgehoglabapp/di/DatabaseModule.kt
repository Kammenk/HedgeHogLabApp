package com.example.hedgehoglabapp.di

import android.content.Context
import androidx.room.Room
import com.example.hedgehoglabapp.local.HedgeHogDatabase
import com.example.hedgehoglabapp.local.HedgehogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideHedgeHogDao(database: HedgeHogDatabase): HedgehogDao = database.hedgeHogDao()

    @Provides
    @Singleton
    fun provideHedgeHogDatabase(@ApplicationContext appContext: Context): HedgeHogDatabase {
        return Room.databaseBuilder(
            appContext,
            HedgeHogDatabase::class.java,
            "hedgehog-db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}