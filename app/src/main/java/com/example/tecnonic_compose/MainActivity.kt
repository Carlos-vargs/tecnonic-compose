package com.example.tecnonic_compose

import android.os.Bundle
import android.widget.Toast
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
import com.example.tecnonic_compose.ui.screens.ChangePasswordScreen
import com.example.tecnonic_compose.ui.screens.LanguageScreen
import com.example.tecnonic_compose.ui.screens.LoginScreen
import com.example.tecnonic_compose.ui.screens.TermsConditionsScreen
import com.example.tecnonic_compose.ui.theme.TecnoniccomposeTheme
import kotlinx.coroutines.delay
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val screen = intent.getStringExtra("screen") ?: "default"  // Si no se pasa ningún valor, usa "default"
        setContent {
            TecnoniccomposeTheme {
                var showSplash by remember { mutableStateOf(true) }


                    // Navigate to the correct screen based on the intent
                    when (screen) {
                        "menu" -> MainScreen()
                        else ->
                            if (showSplash) {
                                SplashScreen(onTimeout = { showSplash = false }) // After 3 seconds, go to next screen
                            } else {
                                LoginScreen()
                                }
                            }
                    }


            }
        }
    }




@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    // Effect triggered when SplashScreen is loaded
    LaunchedEffect(Unit) {
        delay(3000) // Wait for 3 seconds
        onTimeout() // Change to the next screen
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo), // App logo
            contentDescription = "Sentinel Prime Logo",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "SENTINEL\nPRIME", // App title
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 28.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        CircularProgressIndicator(color = Color.Black) // Loading indicator
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    TecnoniccomposeTheme {
        SplashScreen(onTimeout = {})
    }
}
