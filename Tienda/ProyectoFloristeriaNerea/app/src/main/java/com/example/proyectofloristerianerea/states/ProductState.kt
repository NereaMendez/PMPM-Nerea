package com.example.proyectofloristerianerea.states

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.proyectofloristerianerea.data.dto.ProductDto
import com.example.proyectofloristerianerea.data.repository.ProductRepository
import com.example.proyectofloristerianerea.utils.NetworkErrorHandler

class ProductState {
    private val productRepository = ProductRepository()

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var products by mutableStateOf<List<ProductDto>>(emptyList())
        private set

    var selectedProduct by mutableStateOf<ProductDto?>(null)
        private set

    suspend fun loadAllProducts() {
        isLoading = true
        errorMessage = null

        try {
            products = productRepository.getAllProducts()
            Log.d("PRODUCT_STATE", "Productos cargados: ${products.size}")
        } catch (e: Exception) {
            Log.e("PRODUCT_STATE", "Error: ${e.message}", e)
            errorMessage = NetworkErrorHandler.getMessage(e)
        } finally {
            isLoading = false
        }
    }

    suspend fun loadProductsByCategory(categoryId: Long) {
        isLoading = true
        errorMessage = null

        try {
            products = productRepository.getProductsByCategory(categoryId)
            Log.d("PRODUCT_STATE", "Productos por categoría $categoryId: ${products.size}")
        } catch (e: Exception) {
            Log.e("PRODUCT_STATE", "Error: ${e.message}", e)
            errorMessage = NetworkErrorHandler.getMessage(e)
        } finally {
            isLoading = false
        }
    }

    suspend fun loadProductById(productId: Long) {
        isLoading = true
        errorMessage = null
        Log.d("PRODUCT_STATE", "Cargando producto ID: $productId")

        try {
            selectedProduct = productRepository.getProductById(productId)
            Log.d("PRODUCT_STATE", "Producto cargado: ${selectedProduct?.name}")
        } catch (e: Exception) {
            Log.e("PRODUCT_STATE", "Error: ${e.message}", e)
            errorMessage = NetworkErrorHandler.getMessage(e)
        } finally {
            isLoading = false
        }
    }
}