package com.example.hedgehoglabapp.model.pexelsitems

import com.google.gson.annotations.SerializedName

data class PexelsImage(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("photographer")
    val creator: String?,
    @SerializedName("src")
    val imageSizeList: SizeImages?,
    @SerializedName("alt")
    val description: String?
)

fun PexelsImage.convertToUiModel() = PexelsImagesItemUiModel(
    imageId = id ?: 0,
    imageUrl = imageSizeList?.largeSize ?: url ?: "",
    imageCreator = creator ?: "",
    imageDescription = description ?: ""
)
