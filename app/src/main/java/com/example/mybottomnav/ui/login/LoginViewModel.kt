package com.example.mybottomnav.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mybottomnav.data.api.ApiConfig
import com.example.mybottomnav.data.remote.user.LoginResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _login = MutableLiveData<LoginResponse>()
    val login: LiveData<LoginResponse> = _login

    companion object {
        private const val TAG = "LoginViewModel"
    }

    fun login(username: String, password: String) {
        _isLoading.value = true
        _error.value = false

        val json = """
            {
            "username": "$username",
            "password": "$password"
            }
""".trimIndent()

        val requestType = "application/json; charset=utf-8".toMediaType()
        val requestBody = json.toRequestBody(requestType)
        val client = ApiConfig.getApiService().login(requestBody)

        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _login.value = response.body()
                    _error.value = false
                } else {
                    _error.value = true
                    Log.e(TAG, "\"onFailure: ${response.message()}\"")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
                _error.value = true
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

}