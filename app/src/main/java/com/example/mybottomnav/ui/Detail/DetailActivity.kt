package com.example.mybottomnav.ui.Detail

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mybottomnav.R
import com.example.mybottomnav.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: PlantsViewModel
//    companion object{
//        const val EXTRA_PLANT = "key_plant"
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        viewModel = ViewModelProvider(
            this
        )[PlantsViewModel::class.java]

        backButtonPressed()

        val tvDetailNama = binding.tvNameDetail
        val tvDetailDesc = binding.tvDescDetail
        val tvCategory = binding.tvCategory
        val tvCare = binding.tvCare
        val tvSize = binding.tvSize

        val plant = intent.getStringExtra("NAME").toString()
        getDetail(plant)

        viewModel.error.observe(this) {
            if (it == true) {
                errorDisplay()
            } else {
                viewModel.plants.observe(this) {
                    tvDetailNama.text = it[0].name.toString()
                    tvDetailDesc.text = it[0].desc.toString()
                    tvCategory.text = it[0].category.toString()
                    tvCare.text = it[0].careLevel.toString()
                    tvSize.text = it[0].size.toString()
                }
            }
        }
        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun getDetail(plant: String) {
        if (plant.contains("cabai")) {
            viewModel.getPlant(1)
            binding.ivDetail.setImageResource(R.drawable.cabai)
        } else if (plant.contains("kentang")) {
            viewModel.getPlant(2)
            binding.ivDetail.setImageResource(R.drawable.kentang)
        } else if (plant.contains("kubis")) {
            viewModel.getPlant(3)
            binding.ivDetail.setImageResource(R.drawable.kubis)
        } else if (plant.contains("merah")) {
            viewModel.getPlant(4)
            binding.ivDetail.setImageResource(R.drawable.bawang_merah)
        } else if (plant.contains("putih")) {
            viewModel.getPlant(5)
            binding.ivDetail.setImageResource(R.drawable.bawang_putih)
        }
    }

    private fun errorDisplay() {
        binding.emptyIcon.visibility = View.VISIBLE
        binding.tvEmpty.visibility = View.VISIBLE
        binding.backgroundLayout.visibility = View.GONE
        binding.infoContainer.visibility = View.GONE
    }


    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.progressBar.bringToFront()
        } else {
            binding.progressBar.visibility = View.GONE
            binding.backgroundLayout.visibility = View.VISIBLE
            binding.infoContainer.visibility = View.VISIBLE
            playAnimation()
        }
    }

    private fun backButtonPressed() {
        binding.buttonBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.backgroundLayout, View.TRANSLATION_Y, 400f, 0f)
            .setDuration(1000).start()

        ObjectAnimator.ofFloat(binding.infoContainer, View.TRANSLATION_Y, -300f, 0f)
            .setDuration(1000).start()


    }

}