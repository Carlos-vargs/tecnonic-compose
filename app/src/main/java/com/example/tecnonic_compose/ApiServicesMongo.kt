package com.example.tecnonic_compose

import retrofit2.Call
import retrofit2.http.GET

interface ApiServiceMongo {
    @GET("Pedido/productos_mas_vendidos")
    fun getProductsMostSold(): Call<List<ProductMostSold>>

    @GET ("ComentariosValoraciones/contar_por_satisfaccion")
    fun getSatisfactionRate(): Call<List<SatisfactionRate>>

    @GET("FAQ/clientes_mas_preguntas")
    fun getUserResponse(): Call<List<UserResponse>>
}
