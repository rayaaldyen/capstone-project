package com.example.mybottomnav.ui.signup

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.mybottomnav.R
import com.example.mybottomnav.ViewModelFactory
import com.example.mybottomnav.databinding.ActivityLoginBinding
import com.example.mybottomnav.databinding.ActivitySignUpBinding
import com.example.mybottomnav.model.UserModel
import com.example.mybottomnav.model.UserPreference
import com.example.mybottomnav.ui.login.LoginActivity
import com.example.mybottomnav.ui.login.LoginViewModel
import com.example.storyapp.utils.isEmailValid
import com.example.storyapp.utils.isPasswordValid

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class SignUpActivity : AppCompatActivity() {
    private lateinit var signUpBinding: ActivitySignUpBinding
    private lateinit var signUpViewModel: SignupViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        signUpBinding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(signUpBinding.root)
        emailHandler()
        buttonHandler()
        passwordHandler()
        setupViewModel()
    }

    private fun buttonHandler() {
        val username = signUpBinding.nameEditText.text.toString()
        val email = signUpBinding.emailEditText.text.toString()
        val password = signUpBinding.passwordEditText.text.toString()
        signUpBinding.signupButton.isEnabled = !TextUtils.isEmpty(username) && isEmailValid(email) && isPasswordValid(password)
        signUpBinding.signupButton.setOnClickListener {
            signUpViewModel.saveUser(UserModel(username, email, password, false))
            AlertDialog.Builder(this).apply {
                setTitle(getString(R.string.signup_pass_tittle))
                setMessage(getString(R.string.signup_successfully))
                setPositiveButton("Login") { _, _ ->
                    val intent = Intent(context, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
                create()
                show()
            }
        }
    }
    private fun emailHandler() {
        signUpBinding.emailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                buttonHandler()
            }

            override fun afterTextChanged(s: Editable) {

            }

        })
    }

    private fun passwordHandler() {
        signUpBinding.passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                buttonHandler()
            }

            override fun afterTextChanged(s: Editable) {

            }

        })
    }

    private fun setupViewModel() {
        signUpViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[SignupViewModel::class.java]
    }
}