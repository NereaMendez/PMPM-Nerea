package com.example.proyectofloristerianerea.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.proyectofloristerianerea.viewmodel.CartViewModel
import com.example.proyectofloristerianerea.viewmodel.ProductsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId: Long,
    navController: NavHostController,
    productsViewModel: ProductsViewModel = viewModel(),
    cartViewModel: CartViewModel = viewModel()
) {
    var quantity by remember { mutableIntStateOf(1) }

    LaunchedEffect(productId) {
        Log.d("PRODUCT_DETAIL", "Cargando producto con ID: $productId")
        try {
            productsViewModel.loadProductById(productId)
        } catch (e: Exception) {
            Log.e("PRODUCT_DETAIL", "Error: ${e.message}", e)
        }
    }

    val product = productsViewModel.selectedProduct
    val isLoading = productsViewModel.isLoading

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del producto") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        when {
            isLoading == true -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            product == null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("❌ Producto no encontrado", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { navController.popBackStack() }) {
                            Text("Volver")
                        }
                    }
                }
            }
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Imagen del producto
                            if (!product.image.isNullOrBlank()) {
                                AsyncImage(
                                    model = "http://10.0.2.2:8080/images/products/${product.image}",
                                    contentDescription = product.name,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                                        .clip(RoundedCornerShape(12.dp)),
                                    error = androidx.compose.ui.res.painterResource(
                                        android.R.drawable.ic_menu_gallery
                                    )
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }

                            Text(
                                text = product.name ?: "Sin nombre",
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Text(
                                text = product.description ?: "Sin descripción",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "Código: ${product.productCode ?: "N/A"}",
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                text = "${"%.2f".format(product.price ?: 0.0)} €",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                            if ((product.discount ?: 0) > 0) {
                                Text(
                                    text = "Descuento: ${product.discount}%",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                            if (product.stock != null) {
                                Text(
                                    text = "Stock disponible: ${product.stock} unidades",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Cantidad:",
                                style = MaterialTheme.typography.titleMedium
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Button(
                                    onClick = { if (quantity > 1) quantity-- },
                                    enabled = quantity > 1,
                                    modifier = Modifier.size(48.dp)
                                ) {
                                    Text(text = "-", fontSize = 24.sp)
                                }

                                Text(
                                    text = quantity.toString(),
                                    style = MaterialTheme.typography.headlineSmall
                                )

                                Button(
                                    onClick = { quantity++ },
                                    modifier = Modifier.size(48.dp)
                                ) {
                                    Text(text = "+", fontSize = 24.sp)
                                }
                            }

                            Button(
                                onClick = {
                                    Log.d("PRODUCT_DETAIL", "Aniadiendo al carrito: ${product.id}, cantidad: $quantity")
                                    cartViewModel.addToCart(product.id ?: 0L, quantity)
                                    quantity = 1
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Añadir al carrito")
                            }
                        }
                    }
                }
            }
        }
    }
}