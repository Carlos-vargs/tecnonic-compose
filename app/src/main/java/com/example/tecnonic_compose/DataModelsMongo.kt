package com.example.tecnonic_compose

import com.google.gson.annotations.SerializedName

// data model for products most sold
data class ProductMostSold(
    @SerializedName("_id") val name: String,
    @SerializedName("total_vendidos") val totalVendidos: Int
)

// data model for satisfaction rate
data class SatisfactionRate(
    @SerializedName("conteo") val satisfactionRate: Int,
    @SerializedName("_id") val satisfaction: String
)

// data model for user response
data class UserResponse(
    @SerializedName("_id") val userId: String, // user ID (email)
    @SerializedName("total_preguntas") val totalQuestions: Int,
    @SerializedName("preguntas") val questions: List<String>,
    @SerializedName("respuestas") val answers: List<Questions>
)

// data model for question
data class Questions(
    @SerializedName("_id") val answerId: String,
    @SerializedName("Respuesta") val answer: String,
    @SerializedName("pregunta_id") val questionId: String
)

