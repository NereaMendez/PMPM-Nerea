package com.example.proyectofloristerianerea.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
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
import com.example.proyectofloristerianerea.data.dto.ProductDto
import com.example.proyectofloristerianerea.navigation.Routes
import com.example.proyectofloristerianerea.viewmodel.CategoriesViewModel
import com.example.proyectofloristerianerea.viewmodel.ProductsViewModel

@Composable
fun ProductsScreen(
    navController: NavHostController,
    productsViewModel: ProductsViewModel = viewModel(),
    categoriesViewModel: CategoriesViewModel = viewModel()
) {
    val products = productsViewModel.products
    val categories = categoriesViewModel.categories
    val isLoading = productsViewModel.isLoading

    var selectedCategoryId by remember { mutableStateOf<Long?>(null) }

    LaunchedEffect(Unit) {
        productsViewModel.loadAllProducts()
        categoriesViewModel.loadCategories()
    }

    LaunchedEffect(selectedCategoryId) {
        if (selectedCategoryId != null) {
            productsViewModel.loadProductsByCategory(selectedCategoryId!!)
        } else {
            productsViewModel.loadAllProducts()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        if (categories.isNotEmpty()) {
            LazyRow(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    FilterChip(
                        selected = selectedCategoryId == null,
                        onClick = { selectedCategoryId = null },
                        label = { Text("Todos") }
                    )
                }
                items(categories) { category ->
                    FilterChip(
                        selected = selectedCategoryId == category.id,
                        onClick = { selectedCategoryId = category.id },
                        label = { Text(category.name) }
                    )
                }
            }
        }

        if (isLoading == true) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(products) { product ->
                    ProductCard(
                        product = product,
                        onClick = {
                            navController.navigate(Routes.productDetail(product.id))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ProductCard(
    product: ProductDto,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Imagen del producto
            if (!product.image.isNullOrBlank()) {
                AsyncImage(
                    model = "http://10.0.2.2:8080/images/products/${product.image}",
                    contentDescription = product.name,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    error = androidx.compose.ui.res.painterResource(
                        android.R.drawable.ic_menu_gallery
                    )
                )
            } else {
                //cuando imagen es null
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Text("🌸", fontSize = 32.sp)
                }
            }
            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.name ?: "Sin nombre",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = product.description ?: "Sin descripción",
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2
                )
                Text(
                    text = "${"%.2f".format(product.price ?: 0.0)} €",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                if ((product.discount ?: 0) > 0) {
                    Text(
                        text = "Descuento: ${product.discount}%",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Ver detalle"
            )
        }
    }
}

@Composable
fun Spacer(modifier: Modifier = Modifier, width: androidx.compose.ui.unit.Dp = 0.dp) {
    if (width > 0.dp) {
        androidx.compose.foundation.layout.Spacer(modifier = Modifier.width(width))
    }
}