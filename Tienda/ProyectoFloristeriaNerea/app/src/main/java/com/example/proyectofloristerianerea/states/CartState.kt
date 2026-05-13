package com.example.proyectofloristerianerea.states

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.proyectofloristerianerea.data.dto.CartResponseDto
import com.example.proyectofloristerianerea.data.repository.CartRepository
import com.example.proyectofloristerianerea.utils.NetworkErrorHandler

class CartState {
    private val cartRepository = CartRepository()

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var cartResponse by mutableStateOf<CartResponseDto?>(null)
        private set

    suspend fun loadCart() {
        isLoading = true
        errorMessage = null

        try {
            cartResponse = cartRepository.getCart()
        } catch (e: Exception) {
            errorMessage = NetworkErrorHandler.getMessage(e)
        } finally {
            isLoading = false
        }
    }

    suspend fun addToCart(productId: Long, units: Int) {
        isLoading = true
        errorMessage = null

        try {
            cartResponse = cartRepository.addToCart(productId, units)
        } catch (e: Exception) {
            errorMessage = NetworkErrorHandler.getMessage(e)
        } finally {
            isLoading = false
        }
    }

    suspend fun removeFromCart(productId: Long) {
        isLoading = true
        errorMessage = null

        try {
            cartResponse = cartRepository.removeFromCart(productId)
        } catch (e: Exception) {
            errorMessage = NetworkErrorHandler.getMessage(e)
        } finally {
            isLoading = false
        }
    }
}