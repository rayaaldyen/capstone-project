package com.example.mybottomnav.data.remote.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpResponse(
    @SerializedName("user")
    val user: User,
    @SerializedName("token")
    val token: String
): Parcelable

@Parcelize
data class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String
) : Parcelable