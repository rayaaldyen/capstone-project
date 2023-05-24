package com.example.mybottomnav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mybottomnav.model.UserPreference
import com.example.mybottomnav.ui.account.AccountViewModel
import com.example.mybottomnav.ui.login.LoginViewModel
import com.example.mybottomnav.ui.signup.SignupViewModel

class ViewModelFactory(private val pref: UserPreference) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(pref) as T
            }
            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                SignupViewModel(pref) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(pref) as T
            }
            modelClass.isAssignableFrom(AccountViewModel::class.java) -> {
                AccountViewModel(pref) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}