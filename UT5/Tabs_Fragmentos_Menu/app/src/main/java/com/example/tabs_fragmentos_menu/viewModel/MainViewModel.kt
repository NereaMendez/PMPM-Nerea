package com.example.tabs_fragmentos_menu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabs_fragmentos_menu.model.Datos
import com.example.tabs_fragmentos_menu.model.MainModel
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val modelo = MainModel()

    private val _datos = MutableLiveData(Datos())
    val datos: LiveData<Datos> get() = _datos

    fun generarNum() {
        viewModelScope.launch {
            _datos.value = modelo.generarNum()
        }
    }

    fun validarBisiesto(respuestaUsuario: Boolean) {
        val numActual = _datos.value?.numGenerado ?: return
        if (numActual == 0) return

        val esBisiestoReal = (numActual % 4 == 0 && (numActual % 100 != 0 || numActual % 400 == 0))
        val estado = if (respuestaUsuario == esBisiestoReal) 0 else -1
        _datos.value = _datos.value?.copy(estado = estado)
    }

    fun validarDivisible(


        div2: Boolean,
        div3: Boolean,
        div5: Boolean,
        div10: Boolean,
        ninguno: Boolean
    ) {
        val numActual = _datos.value?.numGenerado ?: return
        if (numActual == 0) return

        val seleccionados = listOf(


            div2 to 2,
            div3 to 3,
            div5 to 5,
            div10 to 10
        ).filter { it.first }.map { it.second }

        val esCorrecto = if (seleccionados.isEmpty()) {
            ninguno && listOf(2, 3, 5, 10).all { numActual % it != 0 }
        } else {
            !ninguno && seleccionados.all { numActual % it == 0 } &&
                    listOf(2, 3, 5, 10).filter { it !in seleccionados }.all { numActual % it != 0 }
        }

        _datos.value = _datos.value?.copy(estado = if (esCorrecto) 0 else -1)
    }

    //funcion resetear
    fun resetearEstado() {
        _datos.value = _datos.value?.copy(estado = 1)
    }

    //
    fun setEstado(correcto: Boolean) {
        val estado = if (correcto) 0 else -1
        _datos.value = _datos.value?.copy(estado = estado)
    }
}