package com.example.storyapp.utils

import android.text.TextUtils
import android.util.Patterns

fun isEmailValid(email: String): Boolean{
     return Patterns.EMAIL_ADDRESS.matcher(email).matches() && !TextUtils.isEmpty(email)
 }
fun isPasswordValid(password: String): Boolean{
    return password.length >= 8 && !TextUtils.isEmpty(password)
}
