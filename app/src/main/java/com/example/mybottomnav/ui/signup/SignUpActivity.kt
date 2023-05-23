package com.example.mybottomnav.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mybottomnav.R

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_sign_up)
    }
}