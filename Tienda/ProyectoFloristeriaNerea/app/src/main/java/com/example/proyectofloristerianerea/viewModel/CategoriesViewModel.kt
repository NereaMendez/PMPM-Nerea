package com.example.proyectofloristerianerea.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofloristerianerea.states.CategoryState
import kotlinx.coroutines.launch

class CategoriesViewModel : ViewModel() {
    private val state = CategoryState()  //Sin parámetros

    val isLoading get() = state.isLoading
    val errorMessage get() = state.errorMessage
    val categories get() = state.categories

    fun loadCategories() {
        viewModelScope.launch {
            state.loadCategories()
        }
    }
}