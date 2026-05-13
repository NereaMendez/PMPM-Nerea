package com.example.proyectofloristerianerea.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofloristerianerea.states.LoginState
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val state = LoginState()

    val isLoading: Boolean
        get() = state.isLoading

    val errorMessage: String?
        get() = state.errorMessage

    val loginSuccess: Boolean
        get() = state.loginSuccess

    fun login(username: String, password: String) {
        Log.d("LOGIN", " ViewModel.login llamado")
        viewModelScope.launch {
            state.login(username, password)
        }
    }

    fun reset() {
        state.reset()
    }
}