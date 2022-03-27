package com.example.hedgehoglabapp.model.pexelsitems

import android.content.Context
import android.provider.Settings.Global.getString
import com.example.hedgehoglabapp.R
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

fun PexelsImage.convertToUiModel(context: Context) = PexelsImagesItemUiModel(
    imageId = id ?: 0,
    imageUrl = imageSizeList?.largeSize ?: url ?: "",
    imageCreator = context.getString(R.string.creator_name, creator),
    imageDescription = description ?: ""
)
