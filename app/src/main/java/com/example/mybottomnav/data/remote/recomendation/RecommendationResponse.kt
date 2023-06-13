package com.example.mybottomnav.data.remote.recomendation

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecommendationResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("recommendation")
    val recommendation: List<String>,
    @SerializedName("status_code")
    val status_code: Int,
): Parcelable

