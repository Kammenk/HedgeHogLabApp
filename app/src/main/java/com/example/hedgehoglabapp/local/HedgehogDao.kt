package com.example.hedgehoglabapp.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hedgehoglabapp.model.pexelsitems.PexelsImagesItemEntity

@Dao
interface HedgehogDao {

    @Query("SELECT * FROM pexels_items")
    fun getAllItems(): LiveData<List<PexelsImagesItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(pexelsItem: PexelsImagesItemEntity)

    @Query("SELECT * FROM pexels_items WHERE image_id == :itemId")
    suspend fun findItem(itemId: Int): PexelsImagesItemEntity?

    @Query("DELETE FROM pexels_items where image_id = :itemId")
    suspend fun deleteItem(itemId: Int)

    @Query("DELETE FROM pexels_items")
    suspend fun deleteAll()

}