package com.example.hedgehoglabapp.model

import com.example.hedgehoglabapp.model.pexelsitems.PexelsImagesItemUiModel

data class GalleryUiModel(
    val galleryItemList: List<PexelsImagesItemUiModel>,
    val showMainLoader: Boolean = false,
    val showBottomLoader: Boolean = false,
    val hasError: Boolean = false,
)
