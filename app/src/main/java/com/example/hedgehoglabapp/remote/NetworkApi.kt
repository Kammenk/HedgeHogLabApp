package com.example.hedgehoglabapp.remote

import com.example.hedgehoglabapp.model.pexelsitems.PexelsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NetworkApi {

    @GET("curated")
    suspend fun getCuratedImages(
        @Header("Authorization") contentType: String = "563492ad6f91700001000001a05302a407ed4be48fee865ab5ae309a",
        @Query("page") pageNumber: Int,
        @Query("per_page") pageLimit: Int,
    ): Response<PexelsResponse>

    @GET("search")
    suspend fun searchImages(
        @Header("Authorization") contentType: String = "563492ad6f91700001000001a05302a407ed4be48fee865ab5ae309a",
        @Query("query") query: String,
        @Query("page") pageNumber: Int,
        @Query("per_page") pageLimit: Int,
    ): Response<PexelsResponse>

}