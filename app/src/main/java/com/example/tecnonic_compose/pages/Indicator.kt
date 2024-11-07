package com.example.tecnonic_compose.pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tecnonic_compose.GreetingScreen
import com.example.tecnonic_compose.ui.theme.TecnoniccomposeTheme
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Data classes para los datos de la API
data class Producto(
    @SerializedName("Cantidad") val cantidad: Int,
    @SerializedName("Nombre") val nombre: String,
    @SerializedName("PrecioUnitario") val precioUnitario: Double
)

data class Cliente(
    @SerializedName("Nombre") val nombre: String,
    @SerializedName("Correo") val correo: String,
    @SerializedName("Direccion") val direccion: String
)

data class Carrito(
    @SerializedName("_id") val id: String,
    @SerializedName("FechaCreacion") val fechaCreacion: String,
    @SerializedName("Cliente") val cliente: Cliente,
    @SerializedName("Productos") val productos: List<Producto>
)

data class Recomendacion(
    @SerializedName("_id") val id: String,
    @SerializedName("FechaComentario") val fechaComentario: String,
    @SerializedName("Comentario") val comentario: String,
    @SerializedName("Valoracion") val valoracion: Int,
    @SerializedName("Cliente") val cliente: Cliente,
    @SerializedName("Productos") val productos: List<Producto>
)

// Interfaz de la API
interface ApiService {
    @GET("TecnoNic/carrito-precio-mayor-100")
    suspend fun getCarritos(): List<Carrito>

    @GET("TecnoNic/recomendacion-producto-test")
    suspend fun getRecomendaciones(): List<Recomendacion>
}
// Instancia de Retrofit
object RetrofitInstance {
    private const val BASE_URL = "https://4slz48p3-5000.use2.devtunnels.ms/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

@Composable
fun Indicator(modifier: Modifier = Modifier) {
    val carritos = remember { mutableStateListOf<Carrito>() }
    val recomendaciones = remember { mutableStateListOf<Recomendacion>() }
    var errorMessageCarritos by remember { mutableStateOf<String?>(null) }
    var errorMessageRecomendaciones by remember { mutableStateOf<String?>(null) }
    var loadingCarritos by remember { mutableStateOf(true) }
    var loadingRecomendaciones by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        loadingCarritos = true
        errorMessageCarritos = null
        loadingRecomendaciones = true
        errorMessageRecomendaciones = null

        try {
            // Obtener carritos
            val responseCarritos = withContext(Dispatchers.IO) {
                RetrofitInstance.apiService.getCarritos()
            }
            carritos.clear()
            carritos.addAll(responseCarritos.take(5))  // Limitar a 5 registros
            loadingCarritos = false

            // Obtener recomendaciones
            val responseRecomendaciones = withContext(Dispatchers.IO) {
                RetrofitInstance.apiService.getRecomendaciones()
            }
            recomendaciones.clear()
            recomendaciones.addAll(responseRecomendaciones.take(5))  // Limitar a 5 registros
            loadingRecomendaciones = false
        } catch (e: Exception) {
            errorMessageCarritos = "Error al cargar carritos: ${e.localizedMessage}"
            errorMessageRecomendaciones = "Error al cargar recomendaciones: ${e.localizedMessage}"
            Log.e("Indicator", errorMessageCarritos ?: errorMessageRecomendaciones ?: "Error desconocido")
            loadingCarritos = false
            loadingRecomendaciones = false
        }
    }

    // UI
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4)) // Fondo suave
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Obtener carritos de compras de productos de precio mayor a 100",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6200EE),
            modifier = Modifier
                .padding(bottom = 24.dp, top = 30.dp)
                .shadow(4.dp, shape = MaterialTheme.shapes.large) // Sombra más pronunciada
                .clip(MaterialTheme.shapes.large)
                .background(Color(0xFFE3E3E3)) // Fondo más suave para el encabezado
                .padding(16.dp)
        )

        // Carritos
        if (loadingCarritos) {
            CircularProgressIndicator(
                modifier = Modifier.padding(16.dp),
                color = Color(0xFF6200EE)
            )
        } else if (errorMessageCarritos != null) {
            Text(
                text = errorMessageCarritos ?: "Error desconocido",
                color = Color.Red,
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            carritos.forEach { carrito ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                        .shadow(10.dp, shape = MaterialTheme.shapes.medium)
                        .clip(MaterialTheme.shapes.medium)
                        .background(Color.White)
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(
                            text = "Cliente: ${carrito.cliente.nombre}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF6200EE)
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        // Datos del cliente
                        Text(
                            text = "Correo: ${carrito.cliente.correo}",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "Dirección: ${carrito.cliente.direccion}",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "Fecha de Creación: ${carrito.fechaCreacion}",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Productos del carrito
                        Text(
                            text = "Productos:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 8.dp),
                            color = Color(0xFF6200EE)
                        )

                        Divider(modifier = Modifier.padding(vertical = 8.dp)) // Separador

                        carrito.productos.forEach { producto ->
                            Text(
                                text = "- ${producto.nombre} (Cantidad: ${producto.cantidad}, Precio: $${producto.precioUnitario})",
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Recomendaciones
        Text(
            text = "Recomendaciones de productos",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6200EE),
            modifier = Modifier
                .padding(bottom = 24.dp, top = 30.dp)
                .shadow(4.dp, shape = MaterialTheme.shapes.large) // Sombra más pronunciada
                .clip(MaterialTheme.shapes.large)
                .background(Color(0xFFE3E3E3)) // Fondo más suave para el encabezado
                .padding(16.dp)
        )

        if (loadingRecomendaciones) {
            CircularProgressIndicator(
                modifier = Modifier.padding(16.dp),
                color = Color(0xFF6200EE)
            )
        } else if (errorMessageRecomendaciones != null) {
            Text(
                text = errorMessageRecomendaciones ?: "Error desconocido",
                color = Color.Red,
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            recomendaciones.forEach { recomendacion ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                        .shadow(10.dp, shape = MaterialTheme.shapes.medium)
                        .clip(MaterialTheme.shapes.medium)
                        .background(Color.White)
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(
                            text = "Cliente: ${recomendacion.cliente.nombre}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF6200EE)
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        // Datos del cliente
                        Text(
                            text = "Correo: ${recomendacion.cliente.correo}",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "Dirección: ${recomendacion.cliente.direccion}",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "Fecha de Comentario: ${recomendacion.fechaComentario}",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "Comentario: ${recomendacion.comentario}",
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                        Text(
                            text = "Valoración: ${recomendacion.valoracion}",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Productos recomendados
                        Text(
                            text = "Productos:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 8.dp),
                            color = Color(0xFF6200EE)
                        )

                        Divider(modifier = Modifier.padding(vertical = 8.dp)) // Separador

                        recomendacion.productos.forEach { producto ->
                            Text(
                                text = "- ${producto.nombre} (Cantidad: ${producto.cantidad}, Precio: $${producto.precioUnitario})",
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                        }
                    }
                }
            }
        }
    }
}