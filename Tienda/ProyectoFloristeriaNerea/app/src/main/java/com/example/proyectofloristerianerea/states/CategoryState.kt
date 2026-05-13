package com.example.proyectofloristerianerea.states

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.proyectofloristerianerea.data.dto.CategoryDto
import com.example.proyectofloristerianerea.data.repository.CategoryRepository
import com.example.proyectofloristerianerea.utils.NetworkErrorHandler

class CategoryState {
    private val categoryRepository = CategoryRepository()

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var categories by mutableStateOf<List<CategoryDto>>(emptyList())
        private set

    suspend fun loadCategories() {
        isLoading = true
        errorMessage = null

        try {
            categories = categoryRepository.getCategories()
        } catch (e: Exception) {
            errorMessage = NetworkErrorHandler.getMessage(e)
        } finally {
            isLoading = false
        }
    }
}