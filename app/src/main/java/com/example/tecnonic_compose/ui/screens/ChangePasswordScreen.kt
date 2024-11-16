package com.example.tecnonic_compose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.tecnonic_compose.R

@Composable
fun ChangePasswordScreen() {
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisibleCurrent by remember { mutableStateOf(false) }
    var passwordVisibleNew by remember { mutableStateOf(false) }
    var passwordVisibleConfirm by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Header(
            title = "Cambiar Contraseña",
            onBackPressed = { /* Implementar navegación hacia atrás */ }
        )

        Spacer(modifier = Modifier.height(24.dp))

        PasswordInputField(
            label = "Contraseña Actual",
            password = currentPassword,
            onPasswordChange = { currentPassword = it },
            passwordVisible = passwordVisibleCurrent,
            onPasswordVisibilityChange = { passwordVisibleCurrent = !passwordVisibleCurrent }
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordInputField(
            label = "Nueva Contraseña",
            password = newPassword,
            onPasswordChange = { newPassword = it },
            passwordVisible = passwordVisibleNew,
            onPasswordVisibilityChange = { passwordVisibleNew = !passwordVisibleNew }
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordInputField(
            label = "Confirmar Nueva Contraseña",
            password = confirmPassword,
            onPasswordChange = { confirmPassword = it },
            passwordVisible = passwordVisibleConfirm,
            onPasswordVisibilityChange = { passwordVisibleConfirm = !passwordVisibleConfirm }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Ambas contraseñas deberían coincidir",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.error
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Lógica para cambiar la contraseña
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp), // Altura ajustada para menor redondeo
            shape = MaterialTheme.shapes.small, // Forma menos redondeada
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0066FF)) // Color celeste
        ) {
            Text(text = "Cambiar Contraseña", fontSize = 16.sp, color = Color.White)
        }
    }
}

@Composable
fun Header(title: String, onBackPressed: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 0.dp, top = 30.dp, end = 8.dp, bottom = 8.dp), // Ajuste del padding para evitar márgenes laterales
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBackPressed,
            modifier = Modifier.size(48.dp) // Tamaño del botón
        ) {
            Surface(
                shape = CircleShape,
                color = Color(0xFFf4f4f4)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back), // Ícono de retroceso
                    contentDescription = "Volver",
                    modifier = Modifier
                        .size(36.dp)
                        .padding(8.dp) // Padding interno del ícono
                )
            }
        }
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(start = 8.dp) // Espacio entre el botón y el texto
        )
    }
}



@Composable
fun PasswordInputField(
    label: String,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibilityChange: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_lock), // Ícono de candado
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            BasicTextField(
                value = password,
                onValueChange = onPasswordChange,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black)
            )
            IconButton(onClick = onPasswordVisibilityChange) {
                Image(
                    painter = painterResource(
                        id = if (passwordVisible) R.drawable.ic_visibility else R.drawable.ic_visibility
                    ),
                    contentDescription = "Mostrar/Ocultar Contraseña"
                )
            }
        }
        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(top = 4.dp)) // Línea debajo del input
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChangePasswordScreen() {
    ChangePasswordScreen()
}
