package com.example.mybottomnav.ui.recommendation

import android.Manifest
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.mybottomnav.databinding.ActivityRecommendationBinding
import com.example.mybottomnav.ui.result.ResultActivity
import com.google.android.gms.location.LocationServices

class RecommendationActivity : AppCompatActivity() {
    private var _binding: ActivityRecommendationBinding? = null
    private val binding get() = _binding!!
    private lateinit var recommendationViewModel: RecommendationViewModel
    private var result = mutableListOf<String>()

    companion object {
        private const val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION
        private const val locationPermissionRequestCode = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRecommendationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setupViewModel()
        playAnimation()
        backButtonPressed()
        binding.predictButton.setOnClickListener {
            recommendation()
        }

    }

    private fun reqLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(locationPermission),
            locationPermissionRequestCode
        )
    }

    private fun setupViewModel() {
        recommendationViewModel = ViewModelProvider(
            this
        )[RecommendationViewModel::class.java]
    }


    private fun recommendation() {
//        recommendationViewModel.result.removeObservers(this)
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                locationPermission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        val latitude = location.latitude
                        val longitude = location.longitude
                        if (result.isEmpty()) {
                            recommendationViewModel.setRecommendation(latitude, longitude)
                        }
                        recommendationViewModel.result.observe(this) { listResult ->
                            result = listResult as MutableList<String>
                            if (listResult.isNotEmpty()) {
                                startActivity(
                                    Intent(
                                        this@RecommendationActivity,
                                        ResultActivity::class.java
                                    ).apply {
                                        putExtra(
                                            "RESULT",
                                            result.toTypedArray()
                                        )
                                    })
                            }
                        }
                        recommendationViewModel.isLoading.observe(this) {
                            showLoading(it)
                        }
                    }
                }
        } else {
            reqLocationPermission()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        recommendationViewModel.error.removeObservers(this)
        _binding = null
    }

    private fun backButtonPressed() {
        binding.buttonBack.setOnClickListener {
            onBackPressed()
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


    private fun playAnimation() {
        val title =
            ObjectAnimator.ofFloat(binding.predictTitle, View.ALPHA, 1f).setDuration(300)
        val textDashboard =
            ObjectAnimator.ofFloat(binding.textDashboard, View.ALPHA, 1f).setDuration(300)
        val icon =
            ObjectAnimator.ofFloat(binding.predictIcon, View.ALPHA, 1f).setDuration(300)
        val button =
            ObjectAnimator.ofFloat(binding.predictButton, View.ALPHA, 1f).setDuration(300)

        AnimatorSet().apply {
            playSequentially(title, textDashboard, icon, button)
            start()
        }

    }

}