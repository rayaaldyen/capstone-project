package com.example.mybottomnav.ui.welcome

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.mybottomnav.MainActivity
import com.example.mybottomnav.R
import com.example.mybottomnav.databinding.ActivitySplashBinding
import com.example.mybottomnav.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
//        playAnimation()
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }, 3000)
    }

//    private fun playAnimation() {
//        val welcomeImage =
//            ObjectAnimator.ofFloat(binding.welcomeImage, View.ALPHA, 1f).setDuration(500)
//        val name = ObjectAnimator.ofFloat(binding.appName, View.ALPHA, 1f).setDuration(500)
//        val appDesc =
//            ObjectAnimator.ofFloat(binding.appDesc, View.ALPHA, 1f).setDuration(500)
//
//
//        AnimatorSet().apply {
//            playSequentially(
//                welcomeImage,
//                name,
//                appDesc,
//            )
//            startDelay = 500
//            start()
//        }
//    }
}