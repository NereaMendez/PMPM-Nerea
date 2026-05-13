package com.example.proyectofloristerianerea.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.proyectofloristerianerea.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    onLoginSuccess: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val isLoading = loginViewModel.isLoading
    val errorMessage = loginViewModel.errorMessage
    val loginSuccess = loginViewModel.loginSuccess

    LaunchedEffect(loginSuccess) {
        if (loginSuccess == true) {
            onLoginSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🌸 Floristería Nerea",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        if (isLoading == true) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(12.dp))
        }

        val isFormValid = username.isNotBlank() && password.isNotBlank()

        Button(
            onClick = {
                Log.d("LOGIN", "🔐 Botón presionado - Usuario: $username")
                loginViewModel.login(username, password)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = (isLoading != true) && isFormValid
        ) {
            Text("Iniciar sesión")
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(
            onClick = {
                loginViewModel.reset()
                username = ""
                password = ""
            }
        ) {
            Text("Limpiar")
        }
    }
}