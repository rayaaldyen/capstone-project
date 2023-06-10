package com.example.mybottomnav.data.api

import com.example.mybottomnav.data.remote.user.LoginResponse
import com.example.mybottomnav.data.remote.user.SignUpResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun login(
        @Body requestBody: RequestBody
    ): Call<LoginResponse>


    @POST("register")
    fun register(
        @Body requestBody: RequestBody
    ): Call<SignUpResponse>
}