package com.example.proyectofloristerianerea.data.dto

data class ProductDto(
    val id: Long,
    val productCode: String,
    val name: String,
    val description: String,
    val image: String?,
    val price: Double,
    val discount: Int,
    val stock: Int?
)