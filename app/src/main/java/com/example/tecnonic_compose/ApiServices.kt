package com.example.tecnonic_compose

import retrofit2.Call
import retrofit2.http.GET

// Retrofit Service Interface for KPI Data
interface ApiService {
    @GET("CubeData/get-total-sales-by-year")
    fun getKPIData(): Call<List<KPIData>>

    @GET("CubeData/get-orders-by-payment-method")
    fun getPaymentData(): Call<List<PaymentData>>

    @GET("CubeData/get-sales-by-shipping-country")
    fun getShippingData(): Call<List<ShippingData>>
    
    @GET("/CubeData/get-total-tax-by-shipping-state")
    fun getTaxShippingData(): Call<List<TaxShippingData>>
    
    @GET("/CubeData/get-total-quantity-by-product-category")
    fun getQuantityProductData(): Call<List<QuantityProductData>>

    @GET("/CubeData/get-shipping-cost-by-fiscal-year")
    fun getShippingByFiscalYearData(): Call<List<ShippingByFiscalYearData>>

    @GET("/CubeData/get-products-sold-by-brand")
    fun getSoldByBrandData(): Call<List<SoldByBrandData>>

    @GET("/CubeData/get-total-sales-by-shipping-company")
    fun getSalesByShippingCompanyData(): Call<List<SalesByShippingCompanyData>>

    @GET("/CubeData/get-orders-by-order-status")
    fun getOrderStatusData(): Call<List<OrderStatusData>>

    @GET("/CubeData/get-sales-and-quantity-by-month")
    fun getSalesAndQuantityByMonthData(): Call<List<SalesAndQuantityByMonthData>>
    
    
    
    
}
