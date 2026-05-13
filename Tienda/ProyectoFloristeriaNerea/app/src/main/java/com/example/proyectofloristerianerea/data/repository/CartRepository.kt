package com.example.proyectofloristerianerea.data.repository

import com.example.proyectofloristerianerea.data.api.RetrofitClient
import com.example.proyectofloristerianerea.data.dto.NewCartItemDto  // ← Cambia esta importación
import com.example.proyectofloristerianerea.data.dto.CartResponseDto

class CartRepository {
    private val api = RetrofitClient.api

    suspend fun getCart(): CartResponseDto {
        return api.getCart()
    }

    suspend fun addToCart(productId: Long, units: Int): CartResponseDto {
        return api.addToCart(NewCartItemDto(productId, units))  // ← Cambia AddCartDto por NewCartItemDto
    }

    suspend fun removeFromCart(productId: Long): CartResponseDto {
        return api.deleteFromCart(productId)
    }
}