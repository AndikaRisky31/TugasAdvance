package com.andika.tugasadvance.repomodel

import android.util.Log
import com.andika.tugasadvance.api.JsonPlaceholderService
import retrofit2.Call
import java.io.IOException

class ProductRepository(private val productService: JsonPlaceholderService) {

    private val TAG = ProductRepository::class.java.simpleName

    suspend fun getProducts(): List<Product> {
        try {
            val call: Call<ApiResponse> = productService.getProducts()
            val response = call.execute()

            if (response.isSuccessful) {
                val apiResponse: ApiResponse? = response.body()

                requireNotNull(apiResponse) { "Response body is null" }

                val products: List<Product>? = apiResponse.products

                requireNotNull(products) { "Products list is null" }

                Log.d(TAG, "getProducts successful: $products")

                return products
            } else {
                Log.e(TAG, "getProducts unsuccessful. Code: ${response.code()}, Message: ${response.message()}")
            }
        } catch (e: IOException) {
            Log.e(TAG, "IOException: getProducts failed", e)
            throw e
        } catch (e: Exception) {
            Log.e(TAG, "getProducts failed", e)
            throw e
        }

        throw IllegalStateException("Failed to get products")
    }
}
