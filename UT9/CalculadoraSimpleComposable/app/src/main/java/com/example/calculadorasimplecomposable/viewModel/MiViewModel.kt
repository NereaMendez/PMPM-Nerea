package com.example.calculadorasimplecomposable.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculadorasimplecomposable.model.MiEstado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MiViewModel : ViewModel() {
    private val miEstado = MiEstado()
    private val _resultado = MutableStateFlow<Double?>(0.0)
    val resultado: StateFlow<Double?> get() = _resultado

    fun sumar(num1: Double?, num2: Double?) {
        viewModelScope.launch {
            _resultado.value = miEstado.sumar(num1, num2)
        }
    }

    fun restar(num1: Double?, num2: Double?) {
        viewModelScope.launch {
            _resultado.value = miEstado.restar(num1, num2)
        }
    }

    fun multiplicar(num1: Double?, num2: Double?) {
        viewModelScope.launch {
            _resultado.value = miEstado.multiplicar(num1, num2)
        }
    }

    fun dividir(num1: Double?, num2: Double?) {
        viewModelScope.launch {
            _resultado.value = miEstado.dividir(num1, num2)
        }
    }
}