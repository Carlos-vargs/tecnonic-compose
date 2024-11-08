package com.example.tecnonic_compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tecnonic_compose.ui.theme.TecnoniccomposeTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TecnoniccomposeTheme {
                SplashScreen(onTimeout = {
                    startActivity(Intent(this, MainScreenActivity::class.java))
                    finish()
                })
            }
        }
    }
}

// Nueva actividad para la pantalla principal
class MainScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TecnoniccomposeTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(3000) // Pausa de 3 segundos
        onTimeout() // Llama a la función que cambia de actividad
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Sentinel Prime Logo",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "SENTINEL\nPRIME",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 28.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        CircularProgressIndicator(color = Color.Black)
    }
}

@Composable
fun MainScreen() {
    Text(text = "Bienvenido a Sentinel Prime", fontSize = 24.sp, modifier = Modifier.fillMaxSize())
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    TecnoniccomposeTheme {
        SplashScreen(onTimeout = {})
    }
}
