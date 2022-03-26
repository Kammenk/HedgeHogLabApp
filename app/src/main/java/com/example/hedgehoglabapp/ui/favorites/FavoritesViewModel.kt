package com.example.hedgehoglabapp.ui.favorites

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hedgehoglabapp.R
import com.example.hedgehoglabapp.model.GalleryUiModel
import com.example.hedgehoglabapp.model.pexelsitems.PexelsImagesItemEntity
import com.example.hedgehoglabapp.model.pexelsitems.PexelsImagesItemUiModel
import com.example.hedgehoglabapp.model.pexelsitems.convertToUiModel
import com.example.hedgehoglabapp.repository.details.DetailsRepository
import com.example.hedgehoglabapp.ui.dialog.CustomDialog
import com.example.hedgehoglabapp.ui.dialog.RemoveAllItemsAlertDialog
import com.example.hedgehoglabapp.ui.main.OpenItemListener
import com.example.hedgehoglabapp.ui.snackbar.CustomSnackBar
import com.example.hedgehoglabapp.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository
) : ViewModel(), LifecycleObserver, OpenItemListener, DeleteAllItemsListener {

    private var _uiModelLiveData = MediatorLiveData<GalleryUiModel>().apply {
        value = GalleryUiModel(
            galleryItemList = emptyList(),
            showMainLoader = true,
            hasError = false,
            showBottomLoader = false
        )
    }

    private val _customDialogLiveData = SingleLiveEvent<CustomDialog>()
    val customDialogLiveData: LiveData<CustomDialog>
        get() = _customDialogLiveData

    val uiModelLiveData: LiveData<GalleryUiModel>
        get() = _uiModelLiveData

    private var _customSnackBarLiveData: SingleLiveEvent<CustomSnackBar> = SingleLiveEvent()
    val customSnackBarLiveData: LiveData<CustomSnackBar>
        get() = _customSnackBarLiveData

    private var _navigationLiveData: SingleLiveEvent<PexelsImagesItemUiModel> = SingleLiveEvent()
    val navigationLiveData: LiveData<PexelsImagesItemUiModel>
        get() = _navigationLiveData

    init {
        changeLoadingState(true)
        getSavedItems()
    }

    private fun getSavedItems() {
        viewModelScope.launch {
            handleGetSavedItemsResponse(detailsRepository.getAllItems())
        }
    }

    private fun handleGetSavedItemsResponse(allItems: LiveData<List<PexelsImagesItemEntity>>) {
        _uiModelLiveData.addSource(allItems) { response ->
            response.map { it.convertToUiModel() }.let { convertedList ->
                _uiModelLiveData.value = _uiModelLiveData.value?.copy(
                    galleryItemList = convertedList,
                )
            }
        }
        changeLoadingState(false)
    }

    private fun changeLoadingState(mainLoaderState: Boolean, bottomLoaderState: Boolean = false) {
        _uiModelLiveData.value = _uiModelLiveData.value?.copy(
            showMainLoader = mainLoaderState,
            showBottomLoader = bottomLoaderState
        )
    }

    override fun openItem(item: PexelsImagesItemUiModel) {
        _navigationLiveData.value = item
    }

    override fun deleteAllItems() {
        _customDialogLiveData.value = RemoveAllItemsAlertDialog { removeAllItems() }

    }

    private fun removeAllItems() {
        viewModelScope.launch {
            detailsRepository.deleteAll()
            _customSnackBarLiveData.value = CustomSnackBar(R.string.items_removed)
        }

    }
}
