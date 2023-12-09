package com.andika.tugasadvance.productss

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.andika.tugasadvance.api.RetrofitClient
import com.andika.tugasadvance.databinding.ActivityProductsBinding
import com.andika.tugasadvance.repomodel.ProductRepository

class ProductListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductsBinding
    private val viewModel: ProductViewModel by viewModels()
    private val adapter: ProductAdapter by lazy { ProductAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ListProduct.adapter = adapter
        binding.ListProduct.layoutManager = LinearLayoutManager(this)

        viewModel.products.observe(this, Observer { products ->
            adapter.submitList(products)
            // Log the data from the API
            Log.d("p", "Data from API: $products")
        })

        // Menggunakan RetrofitClient untuk menginisialisasi com.andika.tugasadvance.repomodel.ProductRepository di ViewModel
        val productRepository = ProductRepository(RetrofitClient.jsonPlaceholderService)
        viewModel.initProductRepository(productRepository)

        binding.viewModel = viewModel  // Set the viewModel to the binding
        binding.lifecycleOwner = this   // Set the lifecycle owner for observing LiveData

        viewModel.loadProducts()
    }

    // ... (Other methods)
}
