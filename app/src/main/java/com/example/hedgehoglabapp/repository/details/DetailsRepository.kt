package com.example.hedgehoglabapp.repository.details

import androidx.lifecycle.LiveData
import com.example.hedgehoglabapp.model.pexelsitems.PexelsImagesItemEntity

interface DetailsRepository {

    fun getAllItems(): LiveData<List<PexelsImagesItemEntity>>

    suspend fun addItem(pexelsItem: PexelsImagesItemEntity)

    suspend fun findItem(itemId: Int): PexelsImagesItemEntity?

    suspend fun deleteItem(itemId: Int)

    suspend fun deleteAll()
}