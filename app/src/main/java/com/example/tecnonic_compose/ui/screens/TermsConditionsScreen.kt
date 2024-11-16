package com.example.tecnonic_compose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tecnonic_compose.ui.components.Header
import com.example.tecnonic_compose.ui.components.Paragraph

@Composable
fun TermsConditionsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header con el botón de regresar y el título
        Header(
            title = "Términos y Condiciones",
            onBackPressed = { /* Accion para regresar a la pantalla anterior */ }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Contenido de términos y condiciones con scroll
        TermsContent()
    }
}

@Composable
fun TermsContent() {
    // Estado para manejar el desplazamiento de los términos y condiciones
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Text(
            text = "Términos y Condiciones",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Cuatro párrafos de ejemplo para los términos y condiciones
        Paragraph("1. Estos términos y condiciones regulan el uso de esta aplicación. Al utilizarla, aceptas los términos en su totalidad.")
        Paragraph("2. La aplicación se reserva el derecho de modificar estos términos en cualquier momento sin previo aviso.")
        Paragraph("3. Los usuarios son responsables de mantener la confidencialidad de su cuenta y contraseña.")
        Paragraph("4. Esta aplicación no se responsabiliza por cualquier pérdida o daño que surja del uso de la aplicación.")

        // Agrega más párrafos aquí según sea necesario
    }
}

@Preview(showBackground = true)
@Composable
fun TermsScreenPreview() {
    TermsConditionsScreen()
}
