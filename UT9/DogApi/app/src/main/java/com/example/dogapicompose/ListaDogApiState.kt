package com.example.dogapicompose

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.dogapicompose.model.FotosRespuesta

class ListaDogApiState {

    val api = RetrofitApi()

    suspend fun RazasLista(): List<String> = withContext(Dispatchers.IO) {
        try {
            val respuesta = api.servicio.getRazas()

          //para depurar..
            println("Código respuesta: ${respuesta.code()}")
            println("Respuesta exitosa: ${respuesta.isSuccessful}")

            if (respuesta.isSuccessful) {
                val body = respuesta.body()
                if (body != null) {
                    val razas = body.message.keys.toList()
                    println("Razas encontradas: ${razas.size}")
                    razas
                } else {
                    println("Body es NULL")
                    emptyList()
                }
            } else {
                println("Error código: ${respuesta.code()}")
                emptyList()
            }
        } catch (e: Exception) {
            println("EXCEPCIÓN: ${e.message}")
            emptyList()
        }
    }

    suspend fun RecuperarImagenes(raza: String, subraza: String? = null): FotosRespuesta = withContext(Dispatchers.IO) {
        val respuesta = if (subraza.isNullOrEmpty()) {
            api.servicio.getImagenes(raza)
        } else {
            api.servicio.getImagenesSub(raza, subraza)
        }

        if (respuesta.isSuccessful) {
            respuesta.body() ?: FotosRespuesta("error", emptyList())
        } else {
            FotosRespuesta("error", emptyList())
        }
    }
}