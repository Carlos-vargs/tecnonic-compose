package com.example.tecnonic_compose.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tecnonic_compose.MainActivity
import com.example.tecnonic_compose.R
import com.example.tecnonic_compose.ui.components.Header
import com.example.tecnonic_compose.ui.components.Paragraph

@Composable
fun EditProfileScreen() {
    var fullName by remember { mutableStateOf("Testing User") }
    var email by remember { mutableStateOf("testing@gmail.com") }
    var phoneNumber by remember { mutableStateOf("+8801712663389") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current // Obtener el contexto para la navegación

        // Header with title and back button
        Header(
            title = "Editar Profile",
            onBackPressed = {
                val intent = Intent(context, MainActivity::class.java)
                intent.putExtra("setting", "back")
                context.startActivity(intent)
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Profile picture centered with circular shape
        Image(
            painter = painterResource(id = R.drawable.ic_profile), // Replace with actual profile picture resource
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // User name text under the profile image
        Text(
            text = fullName,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Full Name Field
        ProfileInputField(
            label = "Nombre Completo",
            value = fullName,
            onValueChange = { fullName = it },
            leadingIcon = R.drawable.ic_userprofile // Replace with an appropriate icon resource
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Email Field
        ProfileInputField(
            label = "Correo Electronico",
            value = email,
            onValueChange = { email = it },
            leadingIcon = R.drawable.ic_email // Replace with an appropriate icon resource
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Phone Number Field
        ProfileInputField(
            label = "Numero de Telefono",
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            leadingIcon = R.drawable.ic_phone // Replace with an appropriate icon resource
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Joined date text at the bottom
        Text(
            text = "Se unio 21 Enero 2024",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: Int
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Label
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        // Input field with only bottom border
        TextField(
            value = value,
            onValueChange = onValueChange,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = null,
                    tint = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Gray,
                unfocusedIndicatorColor = Color.LightGray
            ),
            singleLine = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen()
}
