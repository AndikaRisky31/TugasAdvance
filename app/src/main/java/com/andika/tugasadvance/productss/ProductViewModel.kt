package com.andika.tugasadvance.productss

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andika.tugasadvance.api.RetrofitClient
import com.andika.tugasadvance.repomodel.Product
import com.andika.tugasadvance.repomodel.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductViewModel : ViewModel() {

    private val productRepository: ProductRepository = ProductRepository(RetrofitClient.jsonPlaceholderService)

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    // Function to load products from the repository
    fun loadProducts() {
        viewModelScope.launch {
            try {
                // Use withContext to switch to the IO dispatcher for network operations
                val result = withContext(Dispatchers.IO) {
                    productRepository.getProducts()
                }

                // Update the MutableLiveData with the result
                _products.value = result
            } catch (e: Exception) {
                // Handle exceptions or errors here
                // You can show an error message to the user or log the error
                e.printStackTrace()
            }
        }
    }

    // If you want to initialize the ProductRepository separately, use this method
    fun initProductRepository(repository: ProductRepository) {
        // productRepository = repository
    }
}
