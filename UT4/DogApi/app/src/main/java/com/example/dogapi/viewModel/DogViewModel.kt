package com.example.dogapi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogapi.model.Datos
import com.example.dogapi.model.MainState
import kotlinx.coroutines.launch

class DogViewModel : ViewModel() {

    val miEstado = MainState()

    private val _datos = MutableLiveData<Datos>()
    val datos: LiveData<Datos> get() = _datos

    private var listaMaestra: List<String> = emptyList()
    private var razaActual = ""
    private var paginaActual = 1

    fun devuelveFotos(raza: String) {

        viewModelScope.launch {

            razaActual = raza
            paginaActual = 1

            val respuesta = miEstado.recuperaFotos(raza)

            if (respuesta.status == "success") {

                listaMaestra = respuesta.message ?: emptyList()

                val listaPrimeraPagina = listaMaestra.take(10).toMutableList()

                _datos.value = Datos(
                    "success",
                    listaMaestra.size / 10,
                    paginaActual,
                    listaPrimeraPagina
                )

            } else {
                _datos.value = Datos("error", null, 0, mutableListOf())
            }
        }
    }

    fun scrollFotos() {

        val actual = _datos.value ?: return

        paginaActual++

        val limite = paginaActual * 10
        val listaAcumulada = listaMaestra.take(limite).toMutableList()

        _datos.value = Datos(
            "success",
            listaMaestra.size / 10,
            paginaActual,
            listaAcumulada
        )
    }
}