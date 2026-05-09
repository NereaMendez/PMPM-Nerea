package com.example.dogapicompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import coil.compose.AsyncImage
import com.example.dogapicompose.viewmodel.DogApiViewModel

@Composable
fun PantallaFotos(raza: String, subraza: String? = null, navController: NavController) {
    val vm: DogApiViewModel = viewModel()

    //LaunchedEffect para que solo cargue una vez??
    LaunchedEffect(raza) {
        vm.CargarDetalle(raza, subraza)
    }

    val perro = vm.detalle.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3EEF7))
    ) {
        Text(

            text = "Fotos de $raza",
            fontSize = 30.sp,
            color = Color.Blue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            textAlign = TextAlign.Center
        )


        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(perro?.message ?: emptyList()) { foto ->                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(Color(0xFFF3EEF7)),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = foto,
                        contentDescription = "Foto de perro $raza",
                        modifier = Modifier.size(180.dp)
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(

                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text(text = "Volver")
            }
        }
    }
}