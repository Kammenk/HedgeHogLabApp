package com.example.hedgehoglabapp.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hedgehoglabapp.model.pexelsitems.PexelsImagesItemEntity

@Database(
    entities = [
        PexelsImagesItemEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class HedgeHogDatabase : RoomDatabase() {
    abstract fun hedgeHogDao(): HedgehogDao
}