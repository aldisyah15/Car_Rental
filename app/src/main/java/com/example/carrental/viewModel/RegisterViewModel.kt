package com.example.carrental.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carrental.api.RetrofitClient
import com.example.carrental.model.RegisterRequest
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    var isSuccess by mutableStateOf(false)

    fun register(name: String, password: String, email: String, phone: String) {
        viewModelScope.launch {
            val reg = RegisterRequest(
                name = name,
                password = password,
                email = email,
                phone_number = phone
            )

            val res = RetrofitClient.instance.registerUser(reg)
            Log.d("REGISTER_DEBUG", "Success: ${res.body()?.token}")
            Log.d("REGISTER_DEBUG", "Raw Response: ${res.raw()}")
            Log.d("REGISTER_DEBUG", "Error Body: ${res.errorBody()?.string()}")

            try {
                isSuccess = true
            }catch (e: Exception) {
                isSuccess = false
            }

        }
    }

}
