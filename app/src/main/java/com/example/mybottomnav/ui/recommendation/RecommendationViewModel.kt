package com.example.mybottomnav.ui.recommendation

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mybottomnav.R
import com.example.mybottomnav.data.api.ApiConfig
import com.example.mybottomnav.data.remote.recomendation.RecommendationResponse
import com.example.mybottomnav.model.UserPreference
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendationViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _result = MutableLiveData<List<String>>()
    val result: LiveData<List<String>> = _result

    fun setRecommendation(lat: Double, lon: Double) {
        _isLoading.value = true
        val token = UserPreference(context).getUser().token
        val json = """
            {
            "lat": "$lat",
            "lon": "$lon"
            }
""".trimIndent()

        val requestType = "application/json; charset=utf-8".toMediaType()
        val requestBody = json.toRequestBody(requestType)
        val client =
            ApiConfig.getApiService().recommendation(
                token = "Bearer $token",
                requestBody
            )

        client.enqueue(object : Callback<RecommendationResponse> {
            override fun onResponse(
                call: Call<RecommendationResponse>,
                response: Response<RecommendationResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && responseBody.status_code == 200) {
                        _result.value = responseBody.recommendation
                        _isLoading.value = false
                        _error.value = false
                    }

                } else {
                    _isLoading.value = false
                    _error.value = true
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RecommendationResponse>, t: Throwable) {
                _isLoading.value = false
                _error.value = true
                Toast.makeText(
                    context,
                    context.getString(R.string.recomendation_failed),
                    Toast.LENGTH_SHORT
                ).show()
            }

        })

    }

}