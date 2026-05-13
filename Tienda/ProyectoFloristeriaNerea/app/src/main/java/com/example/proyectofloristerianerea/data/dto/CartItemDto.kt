package com.example.proyectofloristerianerea.data.dto

data class CartItemDto(
    val productId: Long,
    val productName: String,
    val unitPrice: Double,
    val units: Int,
    val subtotal: Double,
    val updatedAt: String?
)