package com.example.proyectofloristerianerea.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


object SessionManager {
    var token: String? = null
    var username: String? = null
    var cartProductIds: MutableMap<String, Long> = mutableMapOf() // por productName -> productId
}