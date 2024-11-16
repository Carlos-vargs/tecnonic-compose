package com.example.tecnonic_compose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tecnonic_compose.R
import com.example.tecnonic_compose.SettingActivity

@Composable
fun ProfileScreen() {
    val context = LocalContext.current // Obtener el contexto para la navegación

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header con título "Perfil" y botón de editar
        HeaderWithEditButton(
            title = "Perfil",
            onEditClicked = {
                // Navega a EditProfileScreen usando el Intent de SettingActivity
                context.startActivity(SettingActivity.createIntent(context, "editProfile"))
            },
            onBackPressed = { /* Implementar acción para retroceso si es necesario */ }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Imagen de perfil y nombre de usuario
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_profile), // Reemplazar con la imagen real
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Testing User", // Cambia a un nombre de usuario dinámico si está disponible
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Lista de opciones en el perfil
        ProfileOptionItem(
            icon = R.drawable.ic_userprofile, // Cambia con el ícono correcto
            title = "Informacion Personal",
            onClick = { /* Acción para Informacion Personal */ }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProfileOptionItem(
            icon = R.drawable.ic_settings, // Cambia con el ícono correcto
            title = "Ajustes",
            onClick = { /* Acción para Ajustes */ }
        )
    }
}

// Header with title and edit button
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderWithEditButton(
    title: String,
    onEditClicked: () -> Unit,
    onBackPressed: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBackPressed,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFFF4F4F4))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back), // Reemplaza con el ícono de retroceso
                contentDescription = "Back",
                tint = Color.Black // Cambia el color del icono si es necesario
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )

        IconButton(onClick = onEditClicked) {
            Icon(
                painter = painterResource(id = R.drawable.ic_group), // Reemplaza con el ícono de editar
                contentDescription = "Edit"
            )
        }
    }
}

// Composable for each profile option
@Composable
fun ProfileOptionItem(
    icon: Int,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color.Gray
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = title,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f)
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_go), // Replace with arrow icon
            contentDescription = null,
            tint = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}
