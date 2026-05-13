package com.example.proyectofloristerianerea.data.repository

import android.util.Log
import com.example.proyectofloristerianerea.data.api.RetrofitClient
import com.example.proyectofloristerianerea.data.dto.ProductDto

class ProductRepository {
    private val api = RetrofitClient.api

    suspend fun getAllProducts(): List<ProductDto> {
        Log.d("PRODUCT_REPO", "getAllProducts llamada")
        return try {
            val products = api.getProducts()
            Log.d("PRODUCT_REPO", "Productos recibidos: ${products.size}")
            products
        } catch (e: Exception) {
            Log.e("PRODUCT_REPO", "Error: ${e.message}", e)
            throw e
        }
    }

    suspend fun getProductsByCategory(categoryId: Long): List<ProductDto> {
        Log.d("PRODUCT_REPO", "getProductsByCategory: $categoryId")
        return try {
            val products = api.getProductsByCategory(categoryId)
            Log.d("PRODUCT_REPO", "Productos recibidos: ${products.size}")
            products
        } catch (e: Exception) {
            Log.e("PRODUCT_REPO", "Error: ${e.message}", e)
            throw e
        }
    }

    suspend fun getProductById(productId: Long): ProductDto {
        Log.d("PRODUCT_REPO", "getProductById: $productId")
        return try {
            val product = api.getProductById(productId)
            Log.d("PRODUCT_REPO", "Producto recibido: ${product.name}")
            product
        } catch (e: Exception) {
            Log.e("PRODUCT_REPO", "Error: ${e.message}", e)
            throw e
        }
    }
}