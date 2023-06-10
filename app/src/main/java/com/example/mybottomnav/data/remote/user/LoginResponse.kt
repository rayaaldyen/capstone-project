package com.example.mybottomnav.data.remote.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    @SerializedName("user")
    val user: UserLogin,
    @SerializedName("token")
    val token: String
): Parcelable

@Parcelize
data class UserLogin(
    @SerializedName("id")
    val id: Int,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
) : Parcelable
