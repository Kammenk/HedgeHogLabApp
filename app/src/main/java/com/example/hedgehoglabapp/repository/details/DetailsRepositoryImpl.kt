package com.example.hedgehoglabapp.repository.details

import androidx.lifecycle.LiveData
import com.example.hedgehoglabapp.local.HedgehogDao
import com.example.hedgehoglabapp.model.pexelsitems.PexelsImagesItemEntity
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val hedgehogDao: HedgehogDao
) : DetailsRepository {
    override fun getAllItems(): LiveData<List<PexelsImagesItemEntity>> =
        hedgehogDao.getAllItems()

    override suspend fun addItem(pexelsItem: PexelsImagesItemEntity) =
        hedgehogDao.addItem(pexelsItem)

    override suspend fun findItem(itemId: Int): PexelsImagesItemEntity? =
        hedgehogDao.findItem(itemId)

    override suspend fun deleteItem(itemId: Int) =
        hedgehogDao.deleteItem(itemId)

    override suspend fun deleteAll() = hedgehogDao.deleteAll()
}