package com.example.proyectofloristerianerea.data.repository

import android.util.Log
import com.example.proyectofloristerianerea.data.api.RetrofitClient
import com.example.proyectofloristerianerea.data.dto.LoginRequestDto
import com.example.proyectofloristerianerea.domain.SessionManager

class AuthRepository {
    private val api = RetrofitClient.api

    suspend fun login(username: String, password: String): String {
        Log.d("LOGIN", "🔐 Repository.login: $username")
        try {
            val response = api.login(LoginRequestDto(username, password))
            //
            Log.d("LOGIN", "Respuesta recibida , Token: ${response.accessToken.take(20)}...")
            SessionManager.token = response.accessToken
            SessionManager.username = username
            return response.accessToken
        } catch (e: Exception) {
            Log.e("LOGIN", "Error de Repository : ${e.message}", e)
            throw e
        }
    }
}