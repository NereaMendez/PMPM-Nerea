package com.example.recyclerviewcolores.model

import com.example.recyclerviewcolores.Color.Color

class ColoresModel {

    //Colores Lista
    var colores = mutableListOf<Color>(

        Color("Verde", "#FF4CAF50"),
        Color("Amarillo", "#FFEB3B"),
        Color("Azul", "#FF2196F3"),
        Color("Indigo", "#FF3F51B5"),
        Color("Rojo", "#FF0000"),
        Color("Naranja","#ff6f00"),
        Color("Gris", "#9E9E9E"),
        Color("Violeta", "#9C27B0"),
        Color("Rosa", "#FF00FF"),
        Color("Turquesa", "#00BCD4"),
        Color("Morado", "#673AB7"),
        Color("Fucsia", "#FF00FF"),
        Color("Ámbar","#FFC107"),
        Color("Marron","#795548"))

    suspend fun retornarLista() : Datos {
        return Datos("ok", colores)
    }

}