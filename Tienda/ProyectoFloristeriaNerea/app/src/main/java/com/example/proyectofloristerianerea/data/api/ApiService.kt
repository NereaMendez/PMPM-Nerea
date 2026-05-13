package com.example.proyectofloristerianerea.data.api

import com.example.proyectofloristerianerea.data.dto.*
import retrofit2.http.*

interface ApiService {
    // Auth
    @POST("auth/login")
    suspend fun login(@Body body: LoginRequestDto): LoginResponseDto

    // Products
    @GET("products")
    suspend fun getProducts(): List<ProductDto>

    @GET("products/{productId}")  // ← AÑADE ESTE MÉTODO
    suspend fun getProductById(@Path("productId") productId: Long): ProductDto

    @GET("categories/{categoryId}/products")
    suspend fun getProductsByCategory(@Path("categoryId") categoryId: Long): List<ProductDto>

    // Categories
    @GET("categories")
    suspend fun getCategories(): List<CategoryDto>

    // Cart
    @GET("cart")
    suspend fun getCart(): CartResponseDto

    @POST("cart")
    suspend fun addToCart(@Body body: NewCartItemDto): CartResponseDto

    @DELETE("cart/{productId}")
    suspend fun deleteFromCart(@Path("productId") productId: Long): CartResponseDto
}