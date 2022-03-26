package com.example.hedgehoglabapp.ui.details

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hedgehoglabapp.R
import com.example.hedgehoglabapp.model.pexelsitems.PexelsImagesItemEntity
import com.example.hedgehoglabapp.model.pexelsitems.PexelsImagesItemUiModel
import com.example.hedgehoglabapp.repository.details.DetailsRepository
import com.example.hedgehoglabapp.ui.dialog.CustomDialog
import com.example.hedgehoglabapp.ui.dialog.RemoveItemAlertDialog
import com.example.hedgehoglabapp.ui.snackbar.CustomSnackBar
import com.example.hedgehoglabapp.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedState: SavedStateHandle,
    private val detailsRepository: DetailsRepository
) : ViewModel(), LifecycleObserver,
    AddRemoveItemListener {

    private var _uiModelLiveData = MediatorLiveData<PexelsImagesItemUiModel>()
    val uiModelLiveData: LiveData<PexelsImagesItemUiModel>
        get() = _uiModelLiveData

    private var _customSnackBarLiveData: SingleLiveEvent<CustomSnackBar> = SingleLiveEvent()
    val customSnackBarLiveData: LiveData<CustomSnackBar>
        get() = _customSnackBarLiveData

    private val _customDialogLiveData = SingleLiveEvent<CustomDialog>()
    val customDialogLiveData: LiveData<CustomDialog>
        get() = _customDialogLiveData

    init {
        // check if item is saved in db
        savedState.get<PexelsImagesItemUiModel>("itemDetails")?.let { item ->
            checkIfItemIsLiked(item)
        }
    }

    private fun checkIfItemIsLiked(item: PexelsImagesItemUiModel) {
        viewModelScope.launch {
            detailsRepository.findItem(item.imageId)?.let {
                loadItemDetails(PexelsImagesItemUiModel(
                    imageId = it.imageId,
                    imageUrl = it.imageUrl ?: "",
                    imageCreator = it.imageCreator ?: "",
                    imageLarge = it.imageUrl ?: "",
                    imageDescription = it.imageDescription ?: ""
                ).apply {
                    itemIsLiked = true
                })
            } ?: loadItemDetails(item)
        }
    }

    @VisibleForTesting
    fun loadItemDetails(item: PexelsImagesItemUiModel) {
        _uiModelLiveData.value = item
    }

    override fun addItemToFavorites(item: PexelsImagesItemUiModel) {
        if (item.itemIsLiked) {
            _customSnackBarLiveData.value = CustomSnackBar(R.string.item_already_liked)
            return
        }
        viewModelScope.launch {
            detailsRepository.addItem(
                PexelsImagesItemEntity(
                    imageId = item.imageId,
                    imageUrl = item.imageLarge,
                    imageCreator = item.imageCreator,
                    imageDescription = item.imageDescription
                )
            )
            _uiModelLiveData.value = _uiModelLiveData.value?.copy(
                imageId = item.imageId,
                imageUrl = item.imageUrl,
                imageCreator = item.imageCreator,
                imageLarge = item.imageLarge,
                imageDescription = item.imageDescription
            )?.apply {
                itemIsLiked = true
            }
            _customSnackBarLiveData.value = CustomSnackBar(R.string.item_added)
        }
    }

    override fun removeItemFromFavorites(item: PexelsImagesItemUiModel) {
        _customDialogLiveData.value = RemoveItemAlertDialog { removeItem(item) }
    }

    @VisibleForTesting
    private fun removeItem(item: PexelsImagesItemUiModel) {
        viewModelScope.launch {
            detailsRepository.deleteItem(item.imageId)
            _uiModelLiveData.value = _uiModelLiveData.value?.copy(
                imageId = item.imageId,
                imageUrl = item.imageUrl,
                imageCreator = item.imageCreator,
                imageLarge = item.imageLarge,
                imageDescription = item.imageDescription
            )?.apply {
                itemIsLiked = false
            }
            _customSnackBarLiveData.value = CustomSnackBar(R.string.item_removed)
        }
    }

}