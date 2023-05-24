package com.example.mybottomnav.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var user: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)


        loginBinding.tvRegDirect.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        buttonEnable()
        emailHandler()
        passwordHandler()
        setupViewModel()
        loginAction()
    }

    private fun buttonEnable() {
        val email = loginBinding.emailEditText.text.toString()
        val password = loginBinding.passwordEditText.text.toString()
        loginBinding.loginButton.isEnabled = isEmailValid(email) && isPasswordValid(password)
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
        loginBinding.emailEditText.addTextChangedListener(object : TextWatcher {
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
        loginBinding.passwordEditText.addTextChangedListener(object : TextWatcher {
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
        loginBinding.loginButton.setOnClickListener {
            val email = loginBinding.emailEditText.text.toString()
            val password = loginBinding.passwordEditText.text.toString()
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
    }