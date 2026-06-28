package com.example.carrental.model

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val phone_number: String
)
