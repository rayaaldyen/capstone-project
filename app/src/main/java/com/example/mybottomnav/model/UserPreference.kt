package com.example.mybottomnav.model

import android.content.Context

class UserPreference(context: Context) {

    private val preference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "user_pref"
        private const val ID = "id"
        private const val USERNAME = "username"
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
        private const val TOKEN = "token"
    }

    fun getUser(): UserLoginModel {
        val id = preference.getString(ID, null)?.toInt()
        val username = preference.getString(USERNAME, null)
        val email = preference.getString(EMAIL, null)
        val password = preference.getString(PASSWORD, null)
        val token = preference.getString(TOKEN, null)
        return UserLoginModel(id, username, email, password, token)
    }

    fun saveUser(user: UserLoginModel) {
        val edit = preference.edit()
        edit.putString(ID, user.id.toString())
        edit.putString(USERNAME, user.username)
        edit.putString(EMAIL, user.email)
        edit.putString(PASSWORD, user.password)
        edit.putString(TOKEN, user.token)
        edit.apply()
    }


    fun logout() {
        val edit = preference.edit().clear()
        edit.apply()
    }


}