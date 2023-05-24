package com.example.mybottomnav.ui.account

import androidx.lifecycle.*
import com.example.mybottomnav.model.UserModel
import com.example.mybottomnav.model.UserPreference
import kotlinx.coroutines.launch

class AccountViewModel(private val pref: UserPreference) : ViewModel() {
    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }
}