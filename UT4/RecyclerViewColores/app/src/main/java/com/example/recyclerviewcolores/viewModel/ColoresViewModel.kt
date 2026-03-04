package com.example.recyclerviewcolores.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerviewcolores.Color.Color
import com.example.recyclerviewcolores.model.ColoresModel
import com.example.recyclerviewcolores.model.Datos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ColoresViewModel : ViewModel() {
    private var miModelo = ColoresModel()

    private var _datos = MutableStateFlow<Datos>(Datos("", mutableListOf<Color>()))

    val datos : StateFlow<Datos> get() = _datos

    fun retornarLista() {
        viewModelScope.launch {
            _datos.value = miModelo.retornarLista()
        }
    }
}