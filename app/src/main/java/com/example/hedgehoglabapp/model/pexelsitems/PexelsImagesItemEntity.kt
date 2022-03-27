package com.example.hedgehoglabapp.model.pexelsitems

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "pexels_items")
data class PexelsImagesItemEntity(
    @PrimaryKey
    val id: Int? = null,
    @ColumnInfo(name = "image_id")
    val imageId: Int,
    @ColumnInfo(name = "image_url")
    val imageUrl: String?,
    @ColumnInfo(name = "image_creator")
    val imageCreator: String?,
    @ColumnInfo(name = "image_description")
    val imageDescription: String?,
)

fun PexelsImagesItemEntity.convertToUiModel() = PexelsImagesItemUiModel(
    imageId = imageId,
    imageUrl = imageUrl ?: "",
    imageCreator = imageCreator ?: "",
    imageDescription = imageDescription ?: ""
)
