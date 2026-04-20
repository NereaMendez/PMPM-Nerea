package com.example.calculadorasimplecomposable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculadorasimplecomposable.viewModel.MiViewModel

@Composable
fun holaComposable(){


    val miViewModel = viewModel<MiViewModel>()
    val resultado by miViewModel.resultado.collectAsState()

    var numeroUno by rememberSaveable{ mutableStateOf("") }
    var numeroDos by rememberSaveable{ mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()
        .background(Color.Yellow)
        //.padding(400.dp)
        //el box 400 de ancho y 400 de alto

    ){
        Column(
            modifier = Modifier.background(Color.White)
                .fillMaxSize()
                .padding(16.dp)
            //Centrar los elementos
            ,horizontalAlignment = Alignment.CenterHorizontally


        ) {
            Row() {
                Text(
                    text = "Número 1: ",
                    //Atributo negrita:
                    fontWeight = FontWeight.Bold,
                    //Atributo fuente:
                    fontSize = 18.sp

                )

                TextField(
                    value = numeroUno,
                    onValueChange =
                        {
                            //  Log.e("COMPOSE", "Estoy tecleando")
                            numeroUno = it
                        },
                    label = { Text("Inserte un número") },

                    //esto esta en jetpack open compose, la web
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

                )
            }

            Row() {

                Text(
                    text = "Número 2: ",
                    //Atributo negrita:
                    fontWeight = FontWeight.Bold,
                    //Atributo fuente:
                    fontSize = 18.sp

                )

                TextField(
                    value = numeroDos,
                    onValueChange =
                        {
                            //  Log.e("COMPOSE", "Estoy tecleando")
                            numeroDos = it
                        },
                    label = { Text("Inserte un número") },

                    //esto esta en jetpack open compose, la web
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

                )
            }

            //Fila de botones
        }

        Row(
            //fila:
            //horizontalAlignment = Alignment.CenterHorizontally
            //En Row se usa horizontalArrangement

            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-160).dp),  // ← Sube 40dp desde el centro
            horizontalArrangement = Arrangement.Center


        ){
            //4 botones:

            //btn sumar
            Button(onClick = {
                miViewModel.sumar(numeroUno.toDoubleOrNull(), numeroDos.toDoubleOrNull())
            }){
                Text(text = "+")
            }

            //space event para separar

            //btn restar
            Button(onClick = {
                miViewModel.restar(numeroUno.toDoubleOrNull(), numeroDos.toDoubleOrNull())
            }){
                Text(text = "-")
            }

            //btn multiplicar
            Button(onClick = {
                miViewModel.multiplicar(numeroUno.toDoubleOrNull(), numeroDos.toDoubleOrNull())
            }){
                Text(text = "*")
            }

            //btn dividir
            Button(onClick = {
                miViewModel.dividir(numeroUno.toDoubleOrNull(), numeroDos.toDoubleOrNull())
            }){
                Text(text = "/")
            }

        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (80).dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = resultado?.toString() ?: "0.0"
            )
        }
    }

}