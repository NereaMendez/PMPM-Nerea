package com.example.dogapicompose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavApp() {
    val control = rememberNavController()
    NavHost(control, startDestination = "Pantalla inicio") {

        composable("Pantalla inicio") { PantallaRazas(control) }
        composable("Detalle perro/{raza}") {
            val raza = it.arguments?.getString("raza") ?: ""
            PantallaFotos(raza, null, control)
        }
        composable("Detalle perro/{raza}/{subraza}") {
            val raza = it.arguments?.getString("raza") ?: ""
            val subraza = it.arguments?.getString("subraza")
            PantallaFotos(raza, subraza, control)
        }
    }
}