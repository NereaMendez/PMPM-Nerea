package com.example.dogapicompose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dogapicompose.viewmodel.DogApiViewModel

@Composable
fun PantallaRazas(navController: NavController) {

    val vm: DogApiViewModel = viewModel()
    val perros = vm.listaRazas.collectAsState().value

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFF3EEF7))) {
        Text(
            text = "Lista de Razas",
            fontSize = 32.sp,
            color = Color.Blue,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            textAlign = TextAlign.Center
        )


        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(perros) { index, raza ->
                val backgroundColor = if (index % 2 == 0) {
                    Color(0xFFEDE7F6)
                } else {
                    Color.LightGray
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backgroundColor)
                        .clickable {
                            navController.navigate("Detalle perro/$raza")
                        }
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = raza,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (index % 2 == 0) Color.Black else Color.White
                    )
                }
            }
        }
    }
}