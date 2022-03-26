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
    imageUrl = url ?: "",
    imageCreator = context.getString(R.string.creator_name, creator),
    imageLarge = imageSizeList?.largeSize ?: "",
    imageDescription = description ?: ""
)

//{
//    "id": 2880507,
//    "width": 4000,
//    "height": 6000,
//    "url": "https://www.pexels.com/photo/woman-in-white-long-sleeved-top-and-skirt-standing-on-field-2880507/",
//    "photographer": "Deden Dicky Ramdhani",
//    "photographer_url": "https://www.pexels.com/@drdeden88",
//    "photographer_id": 1378810,
//    "avg_color": "#7E7F7B",
//    "src": {
//    "original": "https://images.pexels.com/photos/2880507/pexels-photo-2880507.jpeg",
//    "large2x": "https://images.pexels.com/photos/2880507/pexels-photo-2880507.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
//    "large": "https://images.pexels.com/photos/2880507/pexels-photo-2880507.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
//    "medium": "https://images.pexels.com/photos/2880507/pexels-photo-2880507.jpeg?auto=compress&cs=tinysrgb&h=350",
//    "small": "https://images.pexels.com/photos/2880507/pexels-photo-2880507.jpeg?auto=compress&cs=tinysrgb&h=130",
//    "portrait": "https://images.pexels.com/photos/2880507/pexels-photo-2880507.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800",
//    "landscape": "https://images.pexels.com/photos/2880507/pexels-photo-2880507.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=627&w=1200",
//    "tiny": "https://images.pexels.com/photos/2880507/pexels-photo-2880507.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=200&w=280"
//}
