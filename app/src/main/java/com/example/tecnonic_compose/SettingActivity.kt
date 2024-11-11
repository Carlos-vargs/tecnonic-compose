package com.example.tecnonic_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tecnonic_compose.ui.screens.ChangePasswordScreen
import com.example.tecnonic_compose.ui.screens.LanguageScreen
import com.example.tecnonic_compose.ui.screens.RegisterScreen
import com.example.tecnonic_compose.ui.screens.TermsConditionsScreen
import com.example.tecnonic_compose.ui.theme.TecnoniccomposeTheme

class SettingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Recuperar el parámetro "screen" del Intent
        val screen = intent.getStringExtra("screen") ?: "default"  // Si no se pasa ningún valor, usa "default"

        setContent {
            TecnoniccomposeTheme {
                // Llamar a la función correspondiente según el valor de "screen"
                when (screen) {
                    "language" -> LanguageScreen()
                    "changePassword" -> ChangePasswordScreen()
                    "privacyPolicy" -> TermsConditionsScreen()
                    "register" -> RegisterScreen()
                    else -> DefaultScreen()
                }
            }
        }
    }
}


@Composable
fun DefaultScreen() {
    Text(text = "Pantalla por defecto", modifier = Modifier.padding(16.dp))
    // Contenido para la pantalla por defecto
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TecnoniccomposeTheme {
        DefaultScreen()
    }
}
