package com.example.proyectofloristerianerea.utils

import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object NetworkErrorHandler {
    fun getMessage(e: Exception): String {
        return when (e) {
            is UnknownHostException -> "No hay conexión a internet"
            is ConnectException -> "No se puede conectar con el servidor"
            is SocketTimeoutException -> "El servidor ha tardado demasiado en responder"
            is HttpException -> {
                when (e.code()) {
                    400 -> "Petición incorrecta"
                    401 -> "Usuario o contraseña incorrectos"
                    403 -> "Acceso denegado"
                    404 -> "Recurso no encontrado"
                    500 -> "Error interno del servidor"
                    503 -> "Servidor no disponible"
                    else -> "Error HTTP ${e.code()}"
                }
            }
            is IOException -> "Error de red"
            else -> "Error inesperado: ${e.message}"
        }
    }
}