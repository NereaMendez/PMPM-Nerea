package com.example.proyectofloristerianerea.states

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.proyectofloristerianerea.data.repository.AuthRepository
import com.example.proyectofloristerianerea.domain.SessionManager
import com.example.proyectofloristerianerea.utils.NetworkErrorHandler

class LoginState {
    private val authRepository = AuthRepository()

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var loginSuccess by mutableStateOf(false)
        private set

    suspend fun login(username: String, password: String) {

        Log.d("LOGIN", " State.login iniciado")
        isLoading = true
        errorMessage = null
        loginSuccess = false

        try {
            //logs para probar los errores de red
            Log.d("LOGIN", " Llamando a authRepository.login...")
            val token = authRepository.login(username, password)
            Log.d("LOGIN", " Token recibido: ${token.take(20)}...")
            if (token.isNotEmpty()) {
                loginSuccess = true
                Log.d("LOGIN", "Login exitoso")
            }
        } catch (e: Exception) {
            Log.e("LOGIN", " Error: ${e.message}", e)
            errorMessage = NetworkErrorHandler.getMessage(e)
        } finally {
            isLoading = false
        }
    }

    fun reset() {
        isLoading = false
        errorMessage = null
        loginSuccess = false
    }
}