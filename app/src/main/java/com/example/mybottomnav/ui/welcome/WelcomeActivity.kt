package com.example.mybottomnav.ui.welcome

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mybottomnav.R
import com.example.mybottomnav.databinding.ActivitySignUpBinding
import com.example.mybottomnav.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        playAnimation()
    }

    private fun playAnimation() {
        val welcomeImage =
            ObjectAnimator.ofFloat(binding.welcomeImage, View.ALPHA, 1f).setDuration(500)
        val name = ObjectAnimator.ofFloat(binding.appName, View.ALPHA, 1f).setDuration(500)
        val appDesc =
            ObjectAnimator.ofFloat(binding.appDesc, View.ALPHA, 1f).setDuration(500)
        val startButton =
            ObjectAnimator.ofFloat(binding.startButton, View.ALPHA, 1f).setDuration(500)


        AnimatorSet().apply {
            playSequentially(
                welcomeImage,
                name,
                appDesc,
                startButton

            )
            startDelay = 500
            start()
        }
    }
}