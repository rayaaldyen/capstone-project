package com.example.mybottomnav.ui.predict

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mybottomnav.data.api.ApiConfig
import com.example.mybottomnav.data.remote.prediction.PredictResponse
import com.example.mybottomnav.model.UserPreference
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PredictViewModel(aplication: Application) : AndroidViewModel(aplication) {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _predict = MutableLiveData<PredictResponse>()
    val predict: LiveData<PredictResponse> = _predict

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    companion object{
        private const val TAG = "PredictViewModel"
    }

    fun predict(tanaman: String, luas_panen: Int, produksi: Int, daerah: String){
        _isLoading.value = true
        _error.value = false
        val token = UserPreference(context).getUser().token
        val json = """
            {
            "tanaman": "$tanaman",
            "luas_panen": $luas_panen,
            "produksi": $produksi,
            "daerah": "$daerah"
            }
""".trimIndent()

        val requestType = "application/json; charset=utf-8".toMediaType()
        val requestBody = json.toRequestBody(requestType)
        val client = ApiConfig.getApiService().predict("Bearer $token", requestBody)

        client.enqueue(object : Callback<PredictResponse> {
            override fun onResponse(
                call: Call<PredictResponse>,
                response: Response<PredictResponse>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    _error.value = false
                    _predict.value = response.body()
                } else{
                    _error.value = true
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PredictResponse>, t: Throwable) {
                _isLoading.value = false
                _error.value = true
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }
}