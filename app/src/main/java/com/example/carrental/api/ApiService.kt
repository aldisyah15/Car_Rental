package com.example.carrental.api

import com.example.carrental.model.RegisterRequest
import com.example.carrental.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/register")
    suspend fun registerUser(@Body req: RegisterRequest): Response<RegisterResponse>

    suspend fun getCar() {

    }
}
