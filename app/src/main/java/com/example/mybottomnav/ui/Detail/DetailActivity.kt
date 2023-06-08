package com.example.mybottomnav.ui.Detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.mybottomnav.R
import com.example.mybottomnav.data.Plant
import com.example.mybottomnav.databinding.ActivityDetailBinding
import com.example.mybottomnav.databinding.ActivityLoginBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    companion object{
        const val EXTRA_PLANT = "key_plant"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val tvDetailNama = binding.tvNameDetail
        val tvDetailDesc = binding.tvDescDetail
        val imgDetailPhoto = binding.ivDetail
        val tvCategory = binding.tvCategory
        val tvCare = binding.tvCare
        val tvSize = binding.tvSize
        val tvMarket = binding.tvMarket

        val plant = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra(EXTRA_PLANT, Plant::class.java)
        } else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_PLANT)
        }

        if(plant != null){
            val photo = plant.photoUrl
            imgDetailPhoto.setImageResource(photo)

            val name = plant.nama
            tvDetailNama.text = name

            val desc = plant.detail
            tvDetailDesc.text = desc

            val category = plant.category
            tvCategory.text = category

            val careLevel = plant.careLevel
            tvCare.text = careLevel

            val size = plant.size
            tvSize.text = size

            val marketTrend = plant.marketTrend
            tvMarket.text = marketTrend
        }
    }
}