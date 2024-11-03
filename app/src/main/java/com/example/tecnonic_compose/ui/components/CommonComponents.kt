package com.example.tecnonic_compose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tecnonic_compose.R

@Composable
fun Paragraph(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@Composable
fun Header(title: String, onBackPressed: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackPressed) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground), // Reemplaza con el icono de flecha
                contentDescription = "Regresar"
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
        Spacer(modifier = Modifier.width(48.dp)) // Espacio para centrar el título
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
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = onPasswordVisibilityChange) {
                Icon(
                    imageVector = if (passwordVisible) {
                        ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground) // Icono de "ver"
                    } else {
                        ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground) // Icono de "ocultar"
                    },
                    contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}
