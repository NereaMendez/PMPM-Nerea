package com.example.proyectofloristerianerea.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectofloristerianerea.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    cartViewModel: CartViewModel = viewModel()
) {
    val cartResponse = cartViewModel.cartResponse
    val isLoading = cartViewModel.isLoading

    var productToRemove by remember { mutableStateOf<Long?>(null) }
    var productNameToRemove by remember { mutableStateOf<String?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        cartViewModel.loadCart()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Carrito") }
            )
        }
    ) { paddingValues ->
        if (isLoading == true) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (cartResponse?.items == null || cartResponse.items.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "🛒 Carrito vacío",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Añade productos desde la sección Productos",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(cartResponse.items) { item ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                productToRemove = item.productId
                                productNameToRemove = item.productName
                                showDialog = true
                            }
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = item.productName,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = "Precio unitario: ${"%.2f".format(item.unitPrice)} €",
                                    style = MaterialTheme.typography.bodySmall
                                )
                                Text(
                                    text = "Unidades: ${item.units}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                                Text(
                                    text = "Subtotal: ${"%.2f".format(item.subtotal)} €",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Resumen",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Productos distintos: ${cartResponse.distinctProducts}")
                        Text("Total unidades: ${cartResponse.totalUnits}")
                        Text(
                            text = "Total: ${"%.2f".format(cartResponse.totalPrice)} €",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }

    if (showDialog && productToRemove != null) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                productToRemove = null
                productNameToRemove = null
            },
            title = { Text("Eliminar producto") },
            text = { Text("¿Deseas eliminar $productNameToRemove del carrito?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        productToRemove?.let { id ->
                            cartViewModel.removeFromCart(id)
                        }
                        showDialog = false
                        productToRemove = null
                        productNameToRemove = null
                    }
                ) {
                    Text("Sí")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        productToRemove = null
                        productNameToRemove = null
                    }
                ) {
                    Text("No")
                }
            }
        )
    }
}