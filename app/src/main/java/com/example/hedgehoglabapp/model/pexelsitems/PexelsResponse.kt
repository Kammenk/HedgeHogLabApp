package com.example.hedgehoglabapp.model.pexelsitems

import com.google.gson.annotations.SerializedName

data class PexelsResponse(
    @SerializedName("page")
    val pageNumber: Int?,
    @SerializedName("per_page")
    val pageLimit: String?,
    @SerializedName("photos")
    val images: List<PexelsImage>?,
    @SerializedName("next_page")
    val nextPage: String?
)



