package com.example.mybottomnav.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybottomnav.model.UserModel
import com.example.mybottomnav.model.UserPreference
import kotlinx.coroutines.launch

class SignupViewModel(private val pref: UserPreference) : ViewModel() {
    fun saveUser(user: UserModel) {
        viewModelScope.launch {
            pref.saveUser(user)
        }
    }
}