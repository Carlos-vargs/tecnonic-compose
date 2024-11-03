package com.example.tecnonic_compose.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
import com.example.tecnonic_compose.ui.components.PasswordInputField
import com.example.tecnonic_compose.ui.components.Header

@Composable
fun ChangePasswordScreen() {
    //navController: NavController
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header con el botón de regresar y el título
        Header(
            title = "Cambiar Contraseña",
            onBackPressed = {  }
        )
        // onBackPressed = { navController.popBackStack() }

        Spacer(modifier = Modifier.height(24.dp))

        // Campo para la contraseña actual
        PasswordInputField(
            label = "Contraseña Actual",
            password = currentPassword,
            onPasswordChange = { currentPassword = it },
            passwordVisible = passwordVisible,
            onPasswordVisibilityChange = { passwordVisible = !passwordVisible }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo para la nueva contraseña
        PasswordInputField(
            label = "Nueva Contraseña",
            password = newPassword,
            onPasswordChange = { newPassword = it },
            passwordVisible = passwordVisible,
            onPasswordVisibilityChange = { passwordVisible = !passwordVisible }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo para confirmar la nueva contraseña
        PasswordInputField(
            label = "Confirmar Nueva Contraseña",
            password = confirmPassword,
            onPasswordChange = { confirmPassword = it },
            passwordVisible = passwordVisible,
            onPasswordVisibilityChange = { passwordVisible = !passwordVisible }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Mensaje de advertencia
        Text(
            text = "Ambas contraseñas deberían coincidir",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.error
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón para cambiar contraseña
        Button(
            onClick = {
                // Lógica para cambiar la contraseña
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Cambiar Contraseña")
        }
    }
}


