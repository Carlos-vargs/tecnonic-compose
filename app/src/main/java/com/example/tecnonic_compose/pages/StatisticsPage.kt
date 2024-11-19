package com.example.tecnonic_compose.pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tecnonic_compose.KPIData
import com.example.tecnonic_compose.OrderStatusData
import com.example.tecnonic_compose.PaymentData
import com.example.tecnonic_compose.QuantityProductData
import com.example.tecnonic_compose.RetrofitClient
import com.example.tecnonic_compose.SalesAndQuantityByMonthData
import com.example.tecnonic_compose.SalesByShippingCompanyData
import com.example.tecnonic_compose.ShippingByFiscalYearData
import com.example.tecnonic_compose.ShippingData
import com.example.tecnonic_compose.SoldByBrandData
import com.example.tecnonic_compose.TaxShippingData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun StatisticsPage(modifier: Modifier = Modifier) {
    var kpiData by remember { mutableStateOf<List<KPIData>?>(null) }
    var isKpiLoading by remember { mutableStateOf(true) }

    var paymentData by remember { mutableStateOf<List<PaymentData>?>(null) }
    var isPaymentLoading by remember { mutableStateOf(true) }

    var shippingData by remember { mutableStateOf<List<ShippingData>?>(null) }
    var isShippingLoading by remember { mutableStateOf(true) }

    var taxShippingData by remember { mutableStateOf<List<TaxShippingData>?>(null) }
    var isTaxShippingLoading by remember { mutableStateOf(true) }

    var quantityProductData by remember { mutableStateOf<List<QuantityProductData>?>(null) }
    var isQuantityProductLoading by remember { mutableStateOf(true) }

    var shippingByFiscalYearData by remember { mutableStateOf<List<ShippingByFiscalYearData>?>(null) }
    var isShippingByFiscalYearLoading by remember { mutableStateOf(true) }

    var soldByBrandData by remember { mutableStateOf<List<SoldByBrandData>?>(null) }
    var isSoldByBrandLoading by remember { mutableStateOf(true) }

    var salesByShippingCompanyData by remember { mutableStateOf<List<SalesByShippingCompanyData>?>(null) }
    var isSalesByShippingCompanyLoading by remember { mutableStateOf(true) }

    var orderStatusData by remember { mutableStateOf<List<OrderStatusData>?>(null) }
    var isOrderStatusLoading by remember { mutableStateOf(true) }

    var salesAndQuantityByMonthData by remember { mutableStateOf<List<SalesAndQuantityByMonthData>?>(null) }
    var isSalesAndQuantityByMonthLoading by remember { mutableStateOf(true) }

    // Fetch data for each category
    LaunchedEffect(Unit) {
        // KPI Data fetching
        RetrofitClient.apiService.getKPIData().enqueue(object : Callback<List<KPIData>> {
            override fun onResponse(call: Call<List<KPIData>>, response: Response<List<KPIData>>) {
                if (response.isSuccessful) {
                    kpiData = response.body()
                } else {
                    Log.e("API Error", "KPI Response failed")
                }
                isKpiLoading = false
            }

            override fun onFailure(call: Call<List<KPIData>>, t: Throwable) {
                Log.e("API Error", "Error: ${t.message}")
                isKpiLoading = false
            }
        })

        // Payment Data fetching
        RetrofitClient.apiService.getPaymentData().enqueue(object : Callback<List<PaymentData>> {
            override fun onResponse(call: Call<List<PaymentData>>, response: Response<List<PaymentData>>) {
                if (response.isSuccessful) {
                    paymentData = response.body()
                } else {
                    Log.e("API Error", "Payment Response failed")
                }
                isPaymentLoading = false
            }

            override fun onFailure(call: Call<List<PaymentData>>, t: Throwable) {
                Log.e("API Error", "Error: ${t.message}")
                isPaymentLoading = false
            }
        })

        // Shipping Data fetching
        RetrofitClient.apiService.getShippingData().enqueue(object : Callback<List<ShippingData>> {
            override fun onResponse(call: Call<List<ShippingData>>, response: Response<List<ShippingData>>) {
                if (response.isSuccessful) {
                    shippingData = response.body()
                } else {
                    Log.e("API Error", "Shipping Response failed" +  response.body())
                }
                isShippingLoading = false
            }

            override fun onFailure(call: Call<List<ShippingData>>, t: Throwable) {
                Log.e("API Error", "Error: ${t.message}")
                isShippingLoading = false
            }
        })

        // Tax Shipping Data fetching
        RetrofitClient.apiService.getTaxShippingData().enqueue(object : Callback<List<TaxShippingData>> {
            override fun onResponse(call: Call<List<TaxShippingData>>, response: Response<List<TaxShippingData>>) {
                if (response.isSuccessful) {
                    taxShippingData = response.body()
                } else {
                    Log.e("API Error", "Tax Shipping Response failed")
                }
                isTaxShippingLoading = false
            }

            override fun onFailure(call: Call<List<TaxShippingData>>, t: Throwable) {
                Log.e("API Error", "Error: ${t.message}")
                isTaxShippingLoading = false
            }
        })

        // Quantity Product Data fetching
        RetrofitClient.apiService.getQuantityProductData().enqueue(object : Callback<List<QuantityProductData>> {
            override fun onResponse(call: Call<List<QuantityProductData>>, response: Response<List<QuantityProductData>>) {
                if (response.isSuccessful) {
                    quantityProductData = response.body()?.take(20)  // Limit to 20 records
                } else {
                    Log.e("API Error", "Quantity Product Response failed")
                }
                isQuantityProductLoading = false
            }

            override fun onFailure(call: Call<List<QuantityProductData>>, t: Throwable) {
                Log.e("API Error", "Error: ${t.message}")
                isQuantityProductLoading = false
            }
        })

        // Shipping by Fiscal Year Data fetching
        RetrofitClient.apiService.getShippingByFiscalYearData().enqueue(object : Callback<List<ShippingByFiscalYearData>> {
            override fun onResponse(call: Call<List<ShippingByFiscalYearData>>, response: Response<List<ShippingByFiscalYearData>>) {
                if (response.isSuccessful) {
                    shippingByFiscalYearData = response.body()
                } else {
                    Log.e("API Error", "Shipping by Fiscal Year Response failed")
                }
                isShippingByFiscalYearLoading = false
            }

            override fun onFailure(call: Call<List<ShippingByFiscalYearData>>, t: Throwable) {
                Log.e("API Error", "Error: ${t.message}")
                isShippingByFiscalYearLoading = false
            }
        })

        // Sold by Brand Data fetching
        RetrofitClient.apiService.getSoldByBrandData().enqueue(object : Callback<List<SoldByBrandData>> {
            override fun onResponse(call: Call<List<SoldByBrandData>>, response: Response<List<SoldByBrandData>>) {
                if (response.isSuccessful) {
                    soldByBrandData = response.body()?.take(20)
                } else {
                    Log.e("API Error", "Sold by Brand Response failed")
                }
                isSoldByBrandLoading = false
            }

            override fun onFailure(call: Call<List<SoldByBrandData>>, t: Throwable) {
                Log.e("API Error", "Error: ${t.message}")
                isSoldByBrandLoading = false
            }
        })

        // Sales by Shipping Company Data fetching
        RetrofitClient.apiService.getSalesByShippingCompanyData().enqueue(object : Callback<List<SalesByShippingCompanyData>> {
            override fun onResponse(call: Call<List<SalesByShippingCompanyData>>, response: Response<List<SalesByShippingCompanyData>>) {
                if (response.isSuccessful) {
                    salesByShippingCompanyData = response.body()
                } else {
                    Log.e("API Error", "Sales by Shipping Company Response failed")
                }
                isSalesByShippingCompanyLoading = false
            }

            override fun onFailure(call: Call<List<SalesByShippingCompanyData>>, t: Throwable) {
                Log.e("API Error", "Error: ${t.message}")
                isSalesByShippingCompanyLoading = false
            }
        })

        // Order Status Data fetching
        RetrofitClient.apiService.getOrderStatusData().enqueue(object : Callback<List<OrderStatusData>> {
            override fun onResponse(call: Call<List<OrderStatusData>>, response: Response<List<OrderStatusData>>) {
                if (response.isSuccessful) {
                    orderStatusData = response.body()

                    orderStatusData = orderStatusData
                        ?.distinctBy { it.hierarchyOrder } // Elimina duplicados por 'HierarchyDate'
                } else {
                    Log.e("API Error", "Order Status Response failed")
                }
                isOrderStatusLoading = false
            }

            override fun onFailure(call: Call<List<OrderStatusData>>, t: Throwable) {
                Log.e("API Error", "Error: ${t.message}")
                isOrderStatusLoading = false
            }
        })

        // Sales and Quantity by Month Data fetching
        RetrofitClient.apiService.getSalesAndQuantityByMonthData().enqueue(object : Callback<List<SalesAndQuantityByMonthData>> {
            override fun onResponse(call: Call<List<SalesAndQuantityByMonthData>>, response: Response<List<SalesAndQuantityByMonthData>>) {
                if (response.isSuccessful) {
                    salesAndQuantityByMonthData = response.body()
                    salesAndQuantityByMonthData = salesAndQuantityByMonthData
                        ?.sortedBy { it.hierarchyDate.toInt() } // Convertir 'hierarchyDate' a Int para ordenarlo
                        ?.distinctBy { it.hierarchyDate } // Eliminar duplicados por 'hierarchyDate'
                } else {
                    Log.e("API Error", "Sales and Quantity by Month Response failed")
                }
                isSalesAndQuantityByMonthLoading = false
            }

            override fun onFailure(call: Call<List<SalesAndQuantityByMonthData>>, t: Throwable) {
                Log.e("API Error", "Error: ${t.message}")
                isSalesAndQuantityByMonthLoading = false
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
            text = "Estadísticas Generales",
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
        if (isKpiLoading || isPaymentLoading || isShippingLoading || isTaxShippingLoading || isQuantityProductLoading || isShippingByFiscalYearLoading || isSoldByBrandLoading || isSalesByShippingCompanyLoading || isOrderStatusLoading || isSalesAndQuantityByMonthLoading) {
            CircularProgressIndicator()
        } else {
            // Show KPI Data
            kpiData?.let { data ->
                Text(
                    text = "Estadísticas de Ventas por Año",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 20.dp, end = 10.dp, start = 10.dp, top = 20.dp )
                )
                data.forEach { kpi ->
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
                                text = "Año: ${kpi.year}",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Ventas Totales: $${"%,.2f".format(kpi.totalPrice)}",
                                fontSize = 18.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }

            // Show Payment Data
            paymentData?.let { data ->
                Text(
                    text = "Estadísticas de Métodos de Pago",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 20.dp, end = 10.dp, start = 10.dp, top = 20.dp )
                )
                data.forEach { payment ->
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
                            Text(text = "Método de Pago: ${payment.paymentMethod}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text(text = "Cantidad de Órdenes: ${payment.quantity}", fontSize = 16.sp, color = Color.Gray)
                        }
                    }
                }
            }

            // Show Shipping Data
            shippingData?.let { data ->
                Text(
                    text = "Estadísticas de Envíos",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(bottom = 20.dp, end = 10.dp, start = 10.dp, top = 20.dp)
                )
                data.forEach { shipping ->
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
                            Text(text = "Envío: ${shipping.hierarchyShip}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text(text = "Monto de Ventas por Envío: $${shipping.salesAmountByShip}", fontSize = 16.sp, color = Color.Gray)
                        }
                    }
                }
            }

            // Show Tax Shipping Data
            taxShippingData?.let { data ->
                Text(
                    text = "Estadísticas de Impuestos y Envíos",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 20.dp, end = 10.dp, start = 10.dp, top = 20.dp )
                )
                data.forEach { taxShipping ->
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
                            Text(text = "Envío: ${taxShipping.hierarchyShip}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text(text = "Impuestos Totales: $${taxShipping.sumOfTaxAmount}", fontSize = 16.sp, color = Color.Gray)
                        }
                    }
                }
            }

            // Show Quantity Product Data
            quantityProductData?.let { data ->
                Text(
                    text = "Estadísticas de Cantidad de Categorias",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 20.dp, end = 10.dp, start = 10.dp, top = 20.dp )
                )
                data.forEach { product ->
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
                            Text(text = "Categoria: ${product.hierarchyClassification}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text(text = "Cantidad Totales: ${product.sumOfQuantity}", fontSize = 16.sp, color = Color.Gray)
                        }
                    }
                }
            }

            // Show Shipping By Fiscal Year Data
            shippingByFiscalYearData?.let { data ->
                Text(
                    text = "Estadísticas de Envíos por Año Fiscal",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 20.dp, end = 10.dp, start = 10.dp, top = 20.dp )
                )
                data.forEach { fiscalYear ->
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
                            Text(text = "Año Fiscal: ${fiscalYear.hierarchyFiscal}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text(text = "Costo de Envío: ${fiscalYear.sumOfShippingCost}", fontSize = 16.sp, color = Color.Gray)
                        }
                    }
                }
            }

            // Show Sold By Brand Data
            soldByBrandData?.let { data ->
                Text(
                    text = "Estadísticas de Ventas por Marca",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 20.dp, end = 10.dp, start = 10.dp, top = 20.dp )
                )
                data.forEach { brand ->
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
                            Text(text = "Marca: ${brand.hierarchyClassification}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text(text = "Precio Total: $${"%,.2f".format(brand.kpiTotalPrice)}", fontSize = 16.sp, color = Color.Gray)
                            Text(text = "Suma de Cantidad: ${brand.sumOfQuantity}", fontSize = 16.sp, color = Color.Gray)
                        }
                    }
                }
            }

            // Show Sales By Shipping Company Data
            salesByShippingCompanyData?.let { data ->
                Text(
                    text = "Estadísticas de Ventas por Compañía de Envío",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 20.dp, end = 10.dp, start = 10.dp, top = 20.dp )
                )
                data.forEach { company ->
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
                            Text(text = "Compañía de Envío: ${company.shippingCompany}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text(text = "Ventas totales: $${company.salesAmountByShip}", fontSize = 16.sp, color = Color.Gray)
                        }
                    }
                }
            }

            // Show Order Status Data
            orderStatusData?.let { data ->
                Text(
                    text = "Estadísticas de Estado de Órdenes",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 20.dp, end = 10.dp, start = 10.dp, top = 20.dp )
                )
                data.forEach { status ->
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
                            Text(text = "Orden: ${status.hierarchyOrder}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text(text = "Cantidad de Entrega: ${status.kpiOrderQuantityDelivery}", fontSize = 16.sp, color = Color.Gray)
                        }
                    }
                }
            }

            // Show Sales And Quantity By Month Data
            salesAndQuantityByMonthData?.let { data ->
                Text(
                    text = "Estadísticas de Ventas y Cantidad por Mes",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 20.dp, end = 10.dp, start = 10.dp, top = 20.dp )
                )
                data.forEach { month ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .background(Color(0xFFF0F4F8))
                                .padding(16.dp, bottom  = 30.dp, top = 10.dp)
                        ) {
                            Text(text = "Mes: ${month.hierarchyDate}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text(text = "Ventas Totales: $${"%,.2f".format(month.kpiTotalPrice)}", fontSize = 16.sp, color = Color.Gray)
                            Text(text = "Cantidad vendida: ${month.sumOfQuantity}", fontSize = 16.sp, color = Color.Gray)
                        }
                    }
                }
            }
        }
    }
}