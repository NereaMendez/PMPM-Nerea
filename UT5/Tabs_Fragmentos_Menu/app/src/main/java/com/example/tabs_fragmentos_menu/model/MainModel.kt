package com.example.tabs_fragmentos_menu.model

import kotlin.random.Random

class MainModel {


    //Funcion generar numero
    suspend fun generarNum(): Datos {

        val num = Random.nextInt(1900, 2200)
        return Datos(num, 1)
    }
}