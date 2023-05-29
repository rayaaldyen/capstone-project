package com.example.mybottomnav.ui.signup

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
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
import com.example.mybottomnav.R
import com.example.mybottomnav.ViewModelFactory
import com.example.mybottomnav.databinding.ActivitySignUpBinding
import com.example.mybottomnav.model.UserModel
import com.example.mybottomnav.model.UserPreference
import com.example.mybottomnav.ui.login.LoginActivity
import com.example.storyapp.utils.isEmailValid
import com.example.storyapp.utils.isPasswordValid

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var signUpViewModel: SignupViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvLoginDirect.setOnClickListener {
            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        playAnimation()
        emailHandler()
        buttonHandler()
        passwordHandler()
        setupViewModel()
    }

    private fun buttonHandler() {
        val username = binding.nameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        binding.signupButton.isEnabled = !TextUtils.isEmpty(username) && isEmailValid(email) && isPasswordValid(password)
        binding.signupButton.setOnClickListener {
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
        binding.emailEditText.addTextChangedListener(object : TextWatcher {
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
        binding.passwordEditText.addTextChangedListener(object : TextWatcher {
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

    private fun playAnimation(){
        ObjectAnimator.ofFloat(binding.registerImage, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val tvTitle = ObjectAnimator.ofFloat(binding.tvRegister, View.ALPHA, 1f).setDuration(300)
        val ivUsername = ObjectAnimator.ofFloat(binding.usernameIcon, View.ALPHA, 1f).setDuration(300)
        val etlUsername = ObjectAnimator.ofFloat(binding.nameEditTextLayout, View.ALPHA, 1f).setDuration(300)
        val ivEmail = ObjectAnimator.ofFloat(binding.emailIcon, View.ALPHA, 1f).setDuration(300)
        val etlEmail = ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(300)
        val ivPassword = ObjectAnimator.ofFloat(binding.passwordIcon, View.ALPHA, 1f).setDuration(300)
        val etlPassword = ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(300)
        val btnReg = ObjectAnimator.ofFloat(binding.signupButton, View.ALPHA, 1f).setDuration(300)
        val tlLoginDirector = ObjectAnimator.ofFloat(binding.loginDirector, View.ALPHA, 1f).setDuration(300)

        AnimatorSet().apply {
            playSequentially(tvTitle, ivUsername, etlUsername, ivEmail, etlEmail, ivPassword, etlPassword, btnReg, tlLoginDirector)
            start()
        }
    }
}