package com.example.dogapicompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.dogapicompose.ListaDogApiState
import com.example.dogapicompose.model.FotosRespuesta

class DogApiViewModel: ViewModel() {

    val estado: ListaDogApiState = ListaDogApiState()


    private val _listaRazas = MutableStateFlow<List<String>>(emptyList())
    val listaRazas: StateFlow<List<String>> get() = _listaRazas

    private val _detalle = MutableStateFlow<FotosRespuesta?>(null)
    val detalle: StateFlow<FotosRespuesta?> get() = _detalle

    fun CargarRazas() {

        viewModelScope.launch {
            _listaRazas.value = estado.RazasLista()
        }
    }

    fun CargarDetalle(raza: String, subraza: String? = null) {
        viewModelScope.launch {
            _detalle.value = estado.RecuperarImagenes(raza, subraza)
        }
    }

    init {
        CargarRazas()
    }
}