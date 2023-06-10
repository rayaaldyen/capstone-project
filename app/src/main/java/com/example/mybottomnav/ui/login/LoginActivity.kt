package com.example.mybottomnav.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.mybottomnav.MainActivity
import com.example.mybottomnav.R
import com.example.mybottomnav.ViewModelFactory
import com.example.mybottomnav.data.remote.user.LoginResponse
import com.example.mybottomnav.databinding.ActivityLoginBinding
import com.example.mybottomnav.model.UserLoginModel
import com.example.mybottomnav.model.UserPreference
import com.example.mybottomnav.ui.signup.SignUpActivity
import com.example.storyapp.utils.isEmailValid
import com.example.storyapp.utils.isPasswordValid

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        playAnimation()
        buttonEnable()
        emailHandler()
        passwordHandler()
        loginAction()
        binding.tvRegDirect.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
        loginViewModel.isLoading.observe(this) {
            showLoading(it)
        }
        loginViewModel.login.observe(this) {
            login(it)
        }
        loginViewModel.error.observe(this) { isError ->
            if (isError) {
                errorMessage()
            }
        }

    }

    private fun buttonEnable() {
        val username = binding.usernameEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        binding.loginButton.isEnabled = !TextUtils.isEmpty(username) && isPasswordValid(password)
    }

    private fun setupViewModel() {
        loginViewModel = ViewModelProvider(
            this
        )[LoginViewModel::class.java]
    }

    private fun emailHandler() {
        binding.usernameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                buttonEnable()
            }

            override fun afterTextChanged(s: Editable) {

            }

        })
    }

    private fun passwordHandler() {
        binding.passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                buttonEnable()
            }

            override fun afterTextChanged(s: Editable) {

            }

        })
    }

    private fun loginAction() {
        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            loginViewModel.login(username, password)
        }
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.progressBar.bringToFront()
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
    private fun errorMessage() {
        AlertDialog.Builder(this).apply {
            setTitle(getString(R.string.alert_login))
            setMessage(getString(R.string.login_invalid))
            setPositiveButton(getString(R.string.back)) { dialog, _ ->
                dialog.dismiss()
            }
            create()
            show()
        }
    }

    private fun saveLoginState(loginResponse: LoginResponse) {
        val userPref = UserPreference(this)
        val loginResult = loginResponse.user
        val loginModel = UserLoginModel(
            id = loginResult.id, username = loginResult.username, email = loginResult.email, password = loginResult.password, token = loginResponse.token
        )
        userPref.saveUser(loginModel)
    }

    private fun login(loginResponse: LoginResponse) {
        saveLoginState(loginResponse)
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun playAnimation(){
        ObjectAnimator.ofFloat(binding.loginImage, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val tvTitle = ObjectAnimator.ofFloat(binding.tvLogin, View.ALPHA, 1f).setDuration(300)
        val ivEmail = ObjectAnimator.ofFloat(binding.emailIcon, View.ALPHA, 1f).setDuration(300)
        val etEmail = ObjectAnimator.ofFloat(binding.usernameEditText, View.ALPHA, 1f).setDuration(300)
        val etlEmail = ObjectAnimator.ofFloat(binding.usernameEditTextLayout, View.ALPHA, 1f).setDuration(300)
        val ivPassword = ObjectAnimator.ofFloat(binding.passwordIcon, View.ALPHA, 1f).setDuration(300)
        val etPassword = ObjectAnimator.ofFloat(binding.passwordEditText, View.ALPHA, 1f).setDuration(300)
        val etlPassword = ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(300)
        val btnLogin = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(300)
        val tvReg = ObjectAnimator.ofFloat(binding.registerDirector, View.ALPHA, 1f).setDuration(300)

        AnimatorSet().apply {
            playSequentially(tvTitle, ivEmail, etEmail, etlEmail, ivPassword, etPassword, etlPassword, btnLogin, tvReg)
            start()
        }
    }

}