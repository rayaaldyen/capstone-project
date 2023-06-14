package com.example.mybottomnav.ui.detail

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mybottomnav.R
import com.example.mybottomnav.data.api.ApiConfig
import com.example.mybottomnav.data.remote.recomendation.PlantsItem
import com.example.mybottomnav.data.remote.recomendation.PlantsResponse
import com.example.mybottomnav.model.UserPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlantsViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _plants = MutableLiveData<List<PlantsItem>>()
    val plants: LiveData<List<PlantsItem>> = _plants

    fun getPlant(id: Int) {
        _isLoading.value = true
        val token = UserPreference(context).getUser().token
        val client =
            ApiConfig.getApiService().getPlants(
                token = "Bearer $token",
                id
            )
        client.enqueue(object : Callback<PlantsResponse> {
            override fun onResponse(
                call: Call<PlantsResponse>,
                response: Response<PlantsResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _plants.value = responseBody.plants as List<PlantsItem>?
                        _isLoading.value = false
                        _error.value = false
                    }

                } else {
                    _isLoading.value = false
                    _error.value = true
                    Toast.makeText(context, context.getString(R.string.recomendation_failed), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PlantsResponse>, t: Throwable) {
                _isLoading.value = false
                _error.value = true
                Toast.makeText(
                    context,
                    context.getString(R.string.connection_failed),
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

}