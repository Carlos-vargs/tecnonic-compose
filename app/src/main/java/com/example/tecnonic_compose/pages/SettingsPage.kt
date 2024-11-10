package com.example.tecnonic_compose.pages

import android.content.Intent
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
import com.example.tecnonic_compose.MainActivity
import com.example.tecnonic_compose.MainScreen
import com.example.tecnonic_compose.R
import com.example.tecnonic_compose.SettingActivity
import com.example.tecnonic_compose.ui.screens.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp

@Composable
fun SettingsPage() {

    val context = LocalContext.current  // Obtiene el contexto de la aplicación

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
        SettingsItem(title = "Lenguaje", value = "Español") {
            val intent = Intent(context, SettingActivity::class.java)
            intent.putExtra("screen", "language")
            context.startActivity(intent)
        }
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

        SettingsItem(title = "Mi perfil") {
            val intent = Intent(context, SettingActivity::class.java)
            intent.putExtra("screen", "profile")
            context.startActivity(intent)
        }
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
        SettingsItem(title = "Crear usuario") {
            val intent = Intent(context, SettingActivity::class.java)
            intent.putExtra("screen", "register")
            context.startActivity(intent)
        }
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

        // Sección Seguridad
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Seguridad",
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        SettingsItem(title = "Cambiar la Contraseña") {
            val intent = Intent(context, SettingActivity::class.java)
            intent.putExtra("screen", "changePassword")
            context.startActivity(intent) }
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)


        SettingsItem(title = "Políticas de Privacidad") {
            val intent = Intent(context, SettingActivity::class.java)
            intent.putExtra("screen", "privacyPolicy")
            context.startActivity(intent)

        }
    }
}

@Composable
fun SettingsItem(title: String, value: String? = null, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { onClick() },  // Ejecuta la acción cuando se hace clic
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
