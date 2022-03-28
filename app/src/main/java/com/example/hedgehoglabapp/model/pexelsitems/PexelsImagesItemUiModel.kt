package com.example.hedgehoglabapp.model.pexelsitems

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PexelsImagesItemUiModel(
    val imageId: Int,
    val imageUrl: String,
    val imageCreator: String,
    val imageDescription: String,
): Parcelable {
    var itemIsLiked: Boolean = false
}

fun PexelsImagesItemUiModel.convertToEntity() = PexelsImagesItemEntity(
    imageId = imageId,
    imageUrl = imageUrl,
    imageCreator = imageCreator,
    imageDescription = imageDescription
)