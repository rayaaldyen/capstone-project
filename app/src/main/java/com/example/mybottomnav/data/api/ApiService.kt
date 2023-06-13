package com.example.mybottomnav.data.api

import com.example.mybottomnav.data.remote.recomendation.PlantsResponse
import com.example.mybottomnav.data.remote.recomendation.RecommendationResponse
import com.example.mybottomnav.data.remote.user.LoginResponse
import com.example.mybottomnav.data.remote.user.SignUpResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("login")
    fun login(
        @Body requestBody: RequestBody
    ): Call<LoginResponse>


    @POST("register")
    fun register(
        @Body requestBody: RequestBody
    ): Call<SignUpResponse>

    @POST("recommend")
    fun recommendation(
        @Header("Authorization") token: String,
        @Body requestBody: RequestBody
    ): Call<RecommendationResponse>

    @GET("plants")
    fun getPlants(
        @Header("Authorization") token: String,
        @Query("id") id: Int
    ): Call<PlantsResponse>
}