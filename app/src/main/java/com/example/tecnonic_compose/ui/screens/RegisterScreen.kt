package com.example.tecnonic_compose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tecnonic_compose.R

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    leadingIcon: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray,
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.Start)
        )

        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp),
                    tint = Color.Gray
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Gray,
                unfocusedIndicatorColor = Color.LightGray,
                textColor = Color.Black,
                cursorColor = Color.Gray,
                placeholderColor = Color.LightGray
            ),
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = VisualTransformation.None,
            singleLine = true
        )
    }
}

@Composable
fun RegisterScreen() {
    var fullName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top =80.dp, start = 16.dp, end = 16.dp), // Ajuste de padding
        horizontalAlignment = Alignment.Start
    ) {
        // Botón de "volver" en un círculo
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .background(Color.Gray, CircleShape) // Fondo circular
                .clickable { /* Acción de volver */ }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Volver",
                tint = Color.White, // Color del ícono
                modifier = Modifier.size(24.dp) // Tamaño del ícono
            )
        }

        Spacer(modifier = Modifier.height(16.dp)) // Espaciado debajo del botón "volver"

        Text(
            text = "Registrarse",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Campo de Nombre Completo
        CustomOutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = "Nombre Completo",
            placeholder = "Ejemplo: Juan Pérez",
            leadingIcon = R.drawable.ic_lock // Reemplaza con el ícono adecuado
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de Número de Teléfono
        CustomOutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = "Número de Teléfono",
            placeholder = "+505 00000000",
            leadingIcon = R.drawable.ic_visibility // Reemplaza con el ícono adecuado
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de Correo Electrónico
        CustomOutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = "Correo Electrónico",
            placeholder = "ejemplo@gmail.com",
            leadingIcon = R.drawable.ic_email // Reemplaza con el ícono adecuado
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de Contraseña
        CustomOutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = "Contraseña",
            placeholder = "Ingrese su contraseña",
            leadingIcon = R.drawable.ic_visibility // Reemplaza con el ícono adecuado
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /* Acción de registro */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF007BFF)),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(text = "Registrarse", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Ya tengo una cuenta. Iniciar Sesión",
            color = Color.Gray,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { /* Acción de iniciar sesión */ }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}
