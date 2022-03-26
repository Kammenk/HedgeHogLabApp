package com.example.hedgehoglabapp.repository.gallery

import com.example.hedgehoglabapp.model.pexelsitems.PexelsResponse
import com.example.hedgehoglabapp.remote.NetworkApi
import javax.inject.Inject
import retrofit2.Response

class GalleryRepositoryImpl @Inject constructor(private val networkApi: NetworkApi) :
    GalleryRepository {
    override suspend fun getCuratedImages(
        pageNumber: Int,
        pageSize: Int
    ): Response<PexelsResponse> =
        networkApi.getCuratedImages(pageNumber = pageNumber, pageLimit = pageSize)

    override suspend fun searchImages(
        query: String,
        pageNumber: Int,
        pageSize: Int
    ): Response<PexelsResponse> =
        networkApi.searchImages(query = query, pageNumber = pageNumber, pageLimit = pageSize)

}