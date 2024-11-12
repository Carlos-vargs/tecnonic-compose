package com.example.tecnonic_compose.ui.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tecnonic_compose.MainActivity
import com.example.tecnonic_compose.R
import com.example.tecnonic_compose.SettingActivity

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
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current  // Obtiene el contexto de la aplicación

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 65.dp, start = 16.dp, end = 16.dp), // Ajuste de padding
        horizontalAlignment = Alignment.Start
    ) {
        // Botón de "volver" en un círculo
        IconButton(
            onClick = {
                val intent = Intent(context, MainActivity::class.java)
                intent.putExtra("setting", "back")
                context.startActivity(intent)
            },
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFFF4F4F4), CircleShape) // Fondo circular
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Volver",
                tint = Color.Black, // Color del ícono
                modifier = Modifier.size(24.dp) // Tamaño del ícono
            )
        }

        Spacer(modifier = Modifier.height(16.dp)) // Espaciado debajo del botón "volver"

        Text(
            text = "Registrarse",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp, top = 20.dp, start = 100.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Campo de Nombre Completo
        CustomOutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = "Nombre Completo",
            placeholder = "Ejemplo: Juan Pérez",
            leadingIcon = R.drawable.ic_email // Reemplaza con el ícono adecuado
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de Número de Teléfono
        CustomOutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = "Número de Teléfono",
            placeholder = "+505 00000000",
            leadingIcon = R.drawable.ic_phone // Reemplaza con el ícono adecuado
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

        // Campo de contraseña personalizado
        androidx.compose.material3.Text(
            text = "Contraseña",
            color = Color.Gray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                androidx.compose.material3.Icon(
                    painter = painterResource(id = R.drawable.ic_lock),
                    contentDescription = "Contraseña",
                    tint = Color.Gray,
                    modifier = Modifier.size(28.dp)
                        .padding(end = 8.dp) // Tamaño del ícono de candado
                )

                BasicTextField(
                    value = password,
                    onValueChange = { password = it },
                    singleLine = true,
                    textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    decorationBox = { innerTextField ->
                        if (password.isEmpty()) {
                            androidx.compose.material3.Text("******", color = Color.Gray, fontSize = 16.sp)
                        }
                        innerTextField()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.Transparent)
                )

                androidx.compose.material3.IconButton(onClick = {
                    passwordVisible = !passwordVisible
                }) {
                    androidx.compose.material3.Icon(
                        painter = painterResource(id = R.drawable.ic_visibility), // Icono para alternar visibilidad
                        contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp) // Tamaño del ícono de visibilidad
                    )
                }
            }
            // Línea inferior personalizada
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .align(Alignment.BottomStart)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /* Acción de registro */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF007BFF)),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(text = "Registrarse", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}
