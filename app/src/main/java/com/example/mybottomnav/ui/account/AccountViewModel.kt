package com.example.mybottomnav.ui.account

import android.app.Application
import androidx.lifecycle.*
import com.example.mybottomnav.model.UserLoginModel
import com.example.mybottomnav.model.UserPreference
import kotlinx.coroutines.launch

class AccountViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    fun getUser(): UserLoginModel {

        return UserPreference(context).getUser()

    }

    fun logout() {
        viewModelScope.launch {
            UserPreference(context).logout()
        }
    }
}