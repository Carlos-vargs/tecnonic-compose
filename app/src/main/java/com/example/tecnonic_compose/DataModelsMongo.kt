package com.example.tecnonic_compose

import com.google.gson.annotations.SerializedName

// data model for products most sold
data class ProductMostSold(
    @SerializedName("_id") val id: String,
    @SerializedName("total_vendidos") val totalVendidos: Int
)




