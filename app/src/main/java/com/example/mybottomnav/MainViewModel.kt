package com.example.mybottomnav

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mybottomnav.model.UserModel
import com.example.mybottomnav.model.UserPreference
import kotlinx.coroutines.launch

class MainViewModel(private val pref: UserPreference) : ViewModel() {
    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

}