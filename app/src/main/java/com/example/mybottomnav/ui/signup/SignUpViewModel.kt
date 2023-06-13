package com.example.mybottomnav.ui.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mybottomnav.data.api.ApiConfig
import com.example.mybottomnav.data.remote.user.SignUpResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _isRegistered = MutableLiveData<Boolean>()
    val isRegistered: LiveData<Boolean> = _isRegistered

    companion object {
        private const val TAG = "SignUpViewModel"
    }

    fun userSignUp(username: String, email: String, password: String) {
        _isLoading.value = true
        _error.value = false
        _isRegistered.value = false
        val json = """
            {
            "username": "$username",
            "email": "$email",
            "password": "$password"
            }
""".trimIndent()

        val requestType = "application/json; charset=utf-8".toMediaType()
        val requestBody = json.toRequestBody(requestType)
        val client = ApiConfig.getApiService().register(requestBody)

        client.enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _isRegistered.value = true
                    _error.value = false
                } else {
                    _isRegistered.value = false
                    _error.value = true
                    Log.e(TAG, "\"onFailure: ${response.message()}\"")
                }


            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                _isLoading.value = false
                _isRegistered.value = false
                _error.value = true
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }
}