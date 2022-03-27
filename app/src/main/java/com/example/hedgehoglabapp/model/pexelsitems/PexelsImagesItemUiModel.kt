package com.example.hedgehoglabapp.model.pexelsitems

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PexelsImagesItemUiModel(
    val imageId: Int,
    val imageUrl: String,
    val imageCreator: String,
    val imageDescription: String,
): Parcelable {
    var itemIsLiked: Boolean = false
}
