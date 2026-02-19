package com.example.calculadorav2.model


class Model {

    //TextView Grande
    var pantalla: String = "0"
    var ultimoOperador: String = ""
    var primerNum: Double = 0.0
    var hayNuevoCalculo: Boolean = true
    var ultimoCalculoCompleto: String = ""

    //funcion, si se pulsa un num
    fun pulsaNum(nuevoNum: String) {


        if (hayNuevoCalculo) {
            pantalla = nuevoNum
            hayNuevoCalculo = false
        } else {


            if (pantalla == "0") {
                pantalla = nuevoNum
            } else {
                pantalla += nuevoNum
            }
        }
    }

    //funcion, cuando se pulsa un operador, dv false si ya habia un operador
    fun pulsaOperador(operador: String): Boolean {

        if (ultimoOperador.isNotEmpty()) {
            return false
        }

        primerNum = pantalla.toDouble()
        ultimoOperador = operador

        ultimoCalculoCompleto = "$primerNum $operador"

        hayNuevoCalculo = true

        return true


    }

    //Funcion que devuelve el resultado que se muestra en pantalla
    fun pulsaIgual(): String {

        if (ultimoOperador.isEmpty()) {
            return pantalla
        }


        val segundoNum = pantalla.toDouble()

        val resultado = when (ultimoOperador) {
            "+" -> primerNum + segundoNum
            "-" -> primerNum - segundoNum
            "x" -> primerNum * segundoNum
            "/" -> {
                if (segundoNum != 0.0) {
                    primerNum / segundoNum
                } else {
                    Double.NaN
                }

            } else -> segundoNum
        }
        val resultadoStr = if (resultado.isNaN()){
            "Error"
        } else {resultado.toString()}
        ultimoCalculoCompleto = "$primerNum $ultimoOperador $segundoNum = $resultadoStr"
        pantalla = resultadoStr
        ultimoOperador = ""
        hayNuevoCalculo = true

        return pantalla

    }
    fun pulsaClear() {
        pantalla = "0"
        ultimoOperador = ""
        primerNum = 0.0
        hayNuevoCalculo = true
        ultimoCalculoCompleto = ""
    }

}