package com.example.mybottomnav.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.mybottomnav.MainActivity
import com.example.mybottomnav.ViewModelFactory
import com.example.mybottomnav.databinding.ActivityLoginBinding
import com.example.mybottomnav.model.UserModel
import com.example.mybottomnav.model.UserPreference
import com.example.mybottomnav.ui.signup.SignUpActivity
import com.example.storyapp.utils.isEmailValid
import com.example.storyapp.utils.isPasswordValid

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var user: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tvRegDirect.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        playAnimation()
        buttonEnable()
        emailHandler()
        passwordHandler()
        setupViewModel()
        loginAction()
    }

    private fun buttonEnable() {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        binding.loginButton.isEnabled = isEmailValid(email) && isPasswordValid(password)
    }

    private fun setupViewModel() {
        loginViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[LoginViewModel::class.java]

        loginViewModel.getUser().observe(this) { user ->
            this.user = user
        }
    }

    private fun emailHandler() {
        binding.emailEditText.addTextChangedListener(object : TextWatcher {
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
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            when {
                email != user.email -> {
                    AlertDialog.Builder(this).setTitle("Login gagal")
                        .setMessage("Email yang anda masukkan tidak sesuai")
                        .setPositiveButton("Kembali") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
                password != user.password -> {
                    AlertDialog.Builder(this).setTitle("Login gagal")
                        .setMessage("Password yang anda masukkan tidak sesuai")
                        .setPositiveButton("Kembali") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()

                }
                else -> {
                    loginViewModel.login()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                }
            }
        }
    }

    private fun playAnimation(){
        ObjectAnimator.ofFloat(binding.loginImage, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val tvTitle = ObjectAnimator.ofFloat(binding.tvLogin, View.ALPHA, 1f).setDuration(300)
        val ivEmail = ObjectAnimator.ofFloat(binding.emailIcon, View.ALPHA, 1f).setDuration(300)
        val etEmail = ObjectAnimator.ofFloat(binding.emailEditText, View.ALPHA, 1f).setDuration(300)
        val etlEmail = ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(300)
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