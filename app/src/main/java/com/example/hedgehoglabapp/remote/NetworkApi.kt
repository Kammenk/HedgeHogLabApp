package com.example.hedgehoglabapp.remote

import com.example.hedgehoglabapp.BuildConfig
import com.example.hedgehoglabapp.model.pexelsitems.PexelsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NetworkApi {

    @GET("curated")
    suspend fun getCuratedImages(
        @Header("Authorization") contentType: String = BuildConfig.PEXELS_API_KEY,
        @Query("page") pageNumber: Int,
        @Query("per_page") pageLimit: Int,
    ): Response<PexelsResponse>

    @GET("search")
    suspend fun searchImages(
        @Header("Authorization") contentType: String = BuildConfig.PEXELS_API_KEY,
        @Query("query") query: String,
        @Query("page") pageNumber: Int,
        @Query("per_page") pageLimit: Int,
    ): Response<PexelsResponse>

}