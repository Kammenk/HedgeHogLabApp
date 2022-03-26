package com.example.hedgehoglabapp.model.pexelsitems

import com.google.gson.annotations.SerializedName

data class SizeImages(
    @SerializedName("original")
    val originalSize: String?,
    @SerializedName("large")
    val largeSize: String?,
    @SerializedName("medium")
    val mediumSize: String?,
    @SerializedName("small")
    val smallSize: String?,
    @SerializedName("portrait")
    val portraitSize: String?,
    @SerializedName("landscape")
    val landscapeSize: String?,
)
