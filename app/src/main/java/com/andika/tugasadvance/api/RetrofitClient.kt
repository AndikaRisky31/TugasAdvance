package com.andika.tugasadvance.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://dummyjson.com/" // Ganti dengan URL API yang sesuai

    val jsonPlaceholderService: JsonPlaceholderService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JsonPlaceholderService::class.java)
    }
}
