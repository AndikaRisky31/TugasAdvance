package com.andika.tugasadvance.api

import com.andika.tugasadvance.repomodel.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceholderService {
    // Sesuaikan dengan metode yang sebenarnya
    @GET("products")
    fun getProducts(): Call<ApiResponse>
}
