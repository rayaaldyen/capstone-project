package com.example.mybottomnav.data.remote.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PredictResponse(
    @SerializedName("recommendation")
    val recommendation: String? = null
): Parcelable