package com.example.proyectofloristerianerea.data.repository

import com.example.proyectofloristerianerea.data.api.RetrofitClient
import com.example.proyectofloristerianerea.data.dto.CategoryDto

class CategoryRepository {
    private val api = RetrofitClient.api

    suspend fun getCategories(): List<CategoryDto> {
        return api.getCategories()
    }
}