package com.example.calculadorasimplecomposable.model

class MiEstado {
    fun sumar(num1: Double?, num2: Double?): Double? {
        if (num1 == null || num2 == null) return null
        return num1 + num2
    }

    fun restar(num1: Double?, num2: Double?): Double? {
        if (num1 == null || num2 == null) return null
        return num1 - num2
    }

    fun multiplicar(num1: Double?, num2: Double?): Double? {
        if (num1 == null || num2 == null) return null
        return num1 * num2
    }

    fun dividir(num1: Double?, num2: Double?): Double? {
        if (num1 == null || num2 == null) return null
        if (num2 == 0.0) return null
        return num1 / num2
    }
}