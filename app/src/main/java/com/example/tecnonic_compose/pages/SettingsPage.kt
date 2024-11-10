package com.example.tecnonic_compose.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tecnonic_compose.R

@Composable
fun SettingsPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp).padding(top = 60.dp),
    ) {
        // Encabezado
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono de Volver con fondo circular
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Gray.copy(alpha = 0.2f), shape = CircleShape)
                    .clickable { /* Acción de volver */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Volver",
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Ajustes",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Gray.copy(alpha = 0.2f), shape = CircleShape)
                    .clickable { /* Acción de configuración adicional */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logout),
                    contentDescription = "Opciones",
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Sección General
        Text(
            text = "General",
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        SettingsItem(title = "Lenguaje", value = "Español")
        Divider(color = Color.LightGray, thickness = 1.dp)
        SettingsItem(title = "Mi perfil")
        Divider(color = Color.LightGray, thickness = 1.dp)
        SettingsItem(title = "Contáctenos")
        Divider(color = Color.LightGray, thickness = 1.dp)

        // Sección Seguridad
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Seguridad",
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        SettingsItem(title = "Cambiar la Contraseña")
        Divider(color = Color.LightGray, thickness = 1.dp)
        SettingsItem(title = "Políticas de Privacidad")
    }
}

@Composable
fun SettingsItem(title: String, value: String? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { /* Acción al hacer clic en la opción */ },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp, fontWeight = FontWeight.Medium),
            modifier = Modifier.weight(1f)
        )
        if (value != null) {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                textAlign = TextAlign.End
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow),
            contentDescription = "Siguiente",
            tint = Color.Gray,
            modifier = Modifier.size(24.dp) // Aumento de tamaño para los iconos
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    SettingsPage()
}
