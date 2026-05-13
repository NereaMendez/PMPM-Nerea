package com.example.proyectofloristerianerea.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectofloristerianerea.domain.SessionManager
import com.example.proyectofloristerianerea.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val innerNavController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Favorite, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = SessionManager.username ?: "Floristería Nerea")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            SessionManager.token = null
                            SessionManager.username = null
                            navController.navigate(Routes.LOGIN) {
                                popUpTo(Routes.MAIN) { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    ) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Cerrar sesión")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = { innerNavController.navigate(Routes.HOME) },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Inicio") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { innerNavController.navigate(Routes.PRODUCTS) },
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
                    label = { Text("Productos") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { innerNavController.navigate(Routes.CART) },
                    icon = { Icon(Icons.Default.List, contentDescription = null) },
                    label = { Text("Mi Carro") }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = innerNavController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Routes.HOME) {
                HomeScreen()
            }
            composable(Routes.PRODUCTS) {
                ProductsScreen(navController = innerNavController)
            }
            composable(Routes.CART) {
                CartScreen()
            }

            composable(
                route = Routes.PRODUCT_DETAIL,
                arguments = listOf(navArgument("productId") { type = NavType.LongType })
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getLong("productId") ?: 0L
                ProductDetailScreen(
                    productId = productId,
                    navController = innerNavController
                )
            }
        }
    }
}