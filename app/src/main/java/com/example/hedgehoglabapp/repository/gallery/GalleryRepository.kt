package com.example.hedgehoglabapp.repository.gallery

import com.example.hedgehoglabapp.model.pexelsitems.PexelsResponse
import retrofit2.Response

interface GalleryRepository {

    suspend fun getCuratedImages(pageNumber: Int, pageSize: Int): Response<PexelsResponse>

    suspend fun searchImages(query: String, pageNumber: Int, pageSize: Int): Response<PexelsResponse>
}