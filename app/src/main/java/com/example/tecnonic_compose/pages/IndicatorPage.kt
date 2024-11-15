package com.example.tecnonic_compose.pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tecnonic_compose.ProductMostSold
import com.example.tecnonic_compose.RetrofitClientMongo
import com.example.tecnonic_compose.SatisfactionRate
import com.example.tecnonic_compose.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun IndicatorPage(modifier: Modifier = Modifier) {
    var productMostSoldData by remember { mutableStateOf<List<ProductMostSold>?>(null) }
    var isProductMostSoldLoading by remember { mutableStateOf(true) }

    var satisfactionRateData by remember { mutableStateOf<List<SatisfactionRate>?>(null) }
    var isSatisfactionRateLoading by remember { mutableStateOf(true) }

    var userResponseData by remember { mutableStateOf<List<UserResponse>?>(null) }
    var isUserResponseLoading by remember { mutableStateOf(true) }


    // Fetch data for each category
    LaunchedEffect(Unit) {
        // Product Most Sold Data fetching
        RetrofitClientMongo.apiServiceMongo.getProductsMostSold().enqueue(object : Callback<List<ProductMostSold>> {
            override fun onResponse(call: Call<List<ProductMostSold>>, response: Response<List<ProductMostSold>>) {
                if (response.isSuccessful) {
                    productMostSoldData = response.body()
                } else {
                    Log.e("API Error", "Product Most Sold Response failed")
                }
                isProductMostSoldLoading = false
            }

            override fun onFailure(call: Call<List<ProductMostSold>>, t: Throwable) {
                Log.e("API Error", "Error: ${t.message}")
                isProductMostSoldLoading = false
            }
        })

        // User Satisfaction Data fetching
        RetrofitClientMongo.apiServiceMongo.getSatisfactionRate().enqueue(object : Callback<List<SatisfactionRate>> {
            override fun onResponse(call: Call<List<SatisfactionRate>>, response: Response<List<SatisfactionRate>>) {
                if (response.isSuccessful) {
                    satisfactionRateData = response.body()
                } else {
                    Log.e("API Error", "User Satisfaction Response failed")
                }
                isSatisfactionRateLoading = false
            }

            override fun onFailure(call: Call<List<SatisfactionRate>>, t: Throwable) {
                Log.e("API Error", "Error: ${t.message}")
                isSatisfactionRateLoading = false
            }
        })

        // User Response Data fetching
        RetrofitClientMongo.apiServiceMongo.getUserResponse().enqueue(object : Callback<List<UserResponse>> {
            override fun onResponse(call: Call<List<UserResponse>>, response: Response<List<UserResponse>>) {
                if (response.isSuccessful) {
                    userResponseData = response.body()
                } else {
                    Log.e("API Error", "User Response failed")
                }
                isUserResponseLoading = false
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                Log.e("API Error", "Error: ${t.message}")
                isUserResponseLoading = false
            }
        })
    }

    // Column layout with scroll support
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mongo Response",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1976D2), // Un azul más profesional
            modifier = Modifier
                .padding(bottom = 35.dp, top = 55.dp, end = 15.dp, start = 15.dp)
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(
                    color = Color(0xFFE3F2FD),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp)
        )

        // Show loading indicator while data is being fetched
        if (isProductMostSoldLoading || isSatisfactionRateLoading || isUserResponseLoading) {
            CircularProgressIndicator()
        } else {
            // Show product most sold data
            productMostSoldData?.let { data ->
                Text(
                    text = "Estadísticas de Productos mas vendidos",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 20.dp, end = 10.dp, start = 10.dp, top = 20.dp )
                )
                data.forEach { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .background(Color(0xFFF0F4F8))
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Producto: ${item.name}",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Total Vendidos: ${item.totalVendidos}",
                                fontSize = 18.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }

            // Show satisfaction rate data
            satisfactionRateData?.let { data ->
                Text(
                    text = "Conteo de Satisfacción",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 20.dp, end = 10.dp, start = 10.dp, top = 20.dp )
                )
                data.forEach { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .background(Color(0xFFF0F4F8))
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Satisfacción: ${item.satisfaction}",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Conteo: ${item.satisfactionRate}",
                                fontSize = 18.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }

            // Show user response data
            userResponseData?.let { data ->
                Text(
                    text = "FAQ usuarios con mas preguntas",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 20.dp, end = 10.dp, start = 10.dp, top = 20.dp )
                )
                data.forEach { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .background(Color(0xFFF0F4F8))
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Usuario: ${item.userId}",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Preguntas: ${item.totalQuestions}",
                                fontSize = 18.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }

}