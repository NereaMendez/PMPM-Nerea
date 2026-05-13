package com.example.proyectofloristerianerea.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofloristerianerea.states.CartState
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    private val state = CartState()  //sin parámetros probar

    val isLoading get() = state.isLoading
    val errorMessage get() = state.errorMessage
    val cartResponse get() = state.cartResponse

    fun loadCart() {
        viewModelScope.launch {
            state.loadCart()
        }
    }

    fun addToCart(productId: Long, units: Int) {
        viewModelScope.launch {
            state.addToCart(productId, units)
        }
    }

    fun removeFromCart(productId: Long) {
        viewModelScope.launch {
            state.removeFromCart(productId)
        }
    }
}