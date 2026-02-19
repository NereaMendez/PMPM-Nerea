package com.example.calculadorav2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculadorav2.model.Model

class CalculadoraViewModel : ViewModel(){

    private val modelo = Model()

    private val _pantallaLiveData = MutableLiveData<String>()
    val pantallaLiveData: LiveData<String> get() = _pantallaLiveData

    private val _recordatorioLiveData = MutableLiveData<String>()
    val recordatorioLiveData: LiveData<String> get() = _recordatorioLiveData

    private val _avisoDobleOperador = MutableLiveData<Boolean>()
    val avisoDobleOperador: LiveData<Boolean> get() = _avisoDobleOperador

    //
    init {
        _pantallaLiveData.value = modelo.pantalla
        _recordatorioLiveData.value = modelo.ultimoCalculoCompleto
        _avisoDobleOperador.value = false
    }

    fun numeroPulsado(digito: String) {
        modelo.pulsaNum(digito)
        _pantallaLiveData.value = modelo.pantalla
        _recordatorioLiveData.value = modelo.ultimoCalculoCompleto
    }

    fun operadorPulsado(operador: String) {
        val operacionValida = modelo.pulsaOperador(operador)
        if (!operacionValida) {
            _avisoDobleOperador.value = true
        }
        _pantallaLiveData.value = modelo.pantalla
        _recordatorioLiveData.value = modelo.ultimoCalculoCompleto
    }

    fun igualPulsado() {
        val resultado = modelo.pulsaIgual()
        _pantallaLiveData.value = resultado
        _recordatorioLiveData.value = modelo.ultimoCalculoCompleto
    }

    fun clearPulsado() {
        modelo.pulsaClear()
        _pantallaLiveData.value = modelo.pantalla
        _recordatorioLiveData.value = modelo.ultimoCalculoCompleto
    }

    fun avisoDobleOperadorConsumido() {
        _avisoDobleOperador.value = false
    }
}



