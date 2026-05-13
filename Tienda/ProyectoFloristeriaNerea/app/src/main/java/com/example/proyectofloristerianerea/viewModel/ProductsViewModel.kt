package com.example.proyectofloristerianerea.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofloristerianerea.states.ProductState
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {
    private val state = ProductState()  //

    val isLoading get() = state.isLoading
    val errorMessage get() = state.errorMessage
    val products get() = state.products
    val selectedProduct get() = state.selectedProduct

    fun loadAllProducts() {
        viewModelScope.launch {
            state.loadAllProducts()
        }
    }

    fun loadProductsByCategory(categoryId: Long) {
        viewModelScope.launch {
            state.loadProductsByCategory(categoryId)
        }
    }

    fun loadProductById(productId: Long) {
        viewModelScope.launch {
            state.loadProductById(productId)
        }
    }
}