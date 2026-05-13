package com.example.proyectofloristerianerea.data.dto

data class CartResponseDto(
    val items: List<CartItemDto>,
    val distinctProducts: Long,
    val totalUnits: Long,
    val totalPrice: Double
)