package com.example.hedgehoglabapp.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.example.hedgehoglabapp.R
import com.example.hedgehoglabapp.model.GalleryUiModel
import com.example.hedgehoglabapp.model.pexelsitems.PexelsImagesItemUiModel
import com.example.hedgehoglabapp.model.pexelsitems.PexelsResponse
import com.example.hedgehoglabapp.model.pexelsitems.convertToUiModel
import com.example.hedgehoglabapp.repository.gallery.GalleryRepository
import com.example.hedgehoglabapp.ui.snackbar.CustomSnackBar
import com.example.hedgehoglabapp.utils.SingleLiveEvent
import com.example.hedgehoglabapp.utils.isNetworkConnected
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import retrofit2.Response

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val galleryRepository: GalleryRepository
) : AndroidViewModel(application), LifecycleObserver, OpenItemListener {

    private var _uiModelLiveData = MediatorLiveData<GalleryUiModel>().apply {
        value = GalleryUiModel(
            galleryItemList = emptyList(),
            showMainLoader = true,
            hasError = false,
            showBottomLoader = false
        )
    }

    val uiModelLiveData: LiveData<GalleryUiModel>
        get() = _uiModelLiveData

    private var _customSnackBarLiveData: SingleLiveEvent<CustomSnackBar> = SingleLiveEvent()
    val customSnackBarLiveData: LiveData<CustomSnackBar>
        get() = _customSnackBarLiveData

    private var _navigationLiveData: SingleLiveEvent<PexelsImagesItemUiModel> = SingleLiveEvent()
    val navigationLiveData: LiveData<PexelsImagesItemUiModel>
        get() = _navigationLiveData

    private var indexReached = DEFAULT_PAGE
    private var resultList: MutableList<PexelsImagesItemUiModel> = mutableListOf()
    private var hasNextPage: Boolean? = true
    private var currentQuery = ""

    init {
        if (!getApplication<Application>().isNetworkConnected()) {
            _customSnackBarLiveData.value = CustomSnackBar(R.string.no_connection)
        } else {
            changeLoadingState(true)
            getCuratedImages(DEFAULT_PAGE, DEFAULT_PAGE_SIZE)
        }
    }

    private fun getCuratedImages(pageNumber: Int, pageSize: Int) {
        viewModelScope.launch {
            handleImagesResponse(galleryRepository.getCuratedImages(pageNumber, pageSize))
        }
    }

    private fun changeLoadingState(mainLoaderState: Boolean, bottomLoaderState: Boolean = false) {
        _uiModelLiveData.value = _uiModelLiveData.value?.copy(
            showMainLoader = mainLoaderState,
            showBottomLoader = bottomLoaderState
        )
    }

    private fun handleImagesResponse(response: Response<PexelsResponse>) {
        when {
            response.isSuccessful && response.body() != null -> {
                response.body()?.images?.let { galleryList ->
                    galleryList.map { it.convertToUiModel(getApplication()) }.let { convertedGalleryList ->
                        resultList.addAll(convertedGalleryList)
                        val finalList =
                            mutableListOf<PexelsImagesItemUiModel>().apply { this.addAll(resultList) }
                        hasNextPage = !response.body()?.nextPage.isNullOrEmpty()
                        _uiModelLiveData.value = _uiModelLiveData.value?.copy(
                            galleryItemList = finalList,
                        )
                    }
                }
            }
            else -> {
                _customSnackBarLiveData.value = CustomSnackBar(R.string.general_error)
            }
        }
        changeLoadingState(false)
    }

    fun searchImages(query: String) {
        // clear all previous request data
        // before calling the search endpoint
        resultList.clear()
        currentQuery = query
        indexReached = DEFAULT_PAGE
        changeLoadingState(true)
        getSearchImages()
    }

    private fun getSearchImages() {
        viewModelScope.launch {
            handleImagesResponse(
                galleryRepository.searchImages(
                    currentQuery, indexReached,
                    DEFAULT_PAGE_SIZE
                )
            )
        }
    }

    fun onScrollAction() {
        if (hasNextPage == true) {
            hasNextPage = false
            indexReached += DEFAULT_PAGE
            changeLoadingState(mainLoaderState = false, bottomLoaderState = true)
            if (currentQuery.isNotEmpty()) {
                getSearchImages()
            } else {
                getCuratedImages(indexReached, DEFAULT_PAGE_SIZE)
            }
        }
    }

    companion object {
        private const val DEFAULT_PAGE = 1
        private const val DEFAULT_PAGE_SIZE = 50
    }

    override fun openItem(item: PexelsImagesItemUiModel) {
        _navigationLiveData.value = item
    }
}