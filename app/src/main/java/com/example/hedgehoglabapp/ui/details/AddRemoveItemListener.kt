package com.example.hedgehoglabapp.ui.details

import com.example.hedgehoglabapp.model.pexelsitems.PexelsImagesItemUiModel

interface AddRemoveItemListener {
    fun addItemToFavorites(item: PexelsImagesItemUiModel)
    fun removeItemFromFavorites(item: PexelsImagesItemUiModel)
}