package com.example.mybottomnav.ui.Detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.mybottomnav.R
import com.example.mybottomnav.data.Plant

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_PLANT = "key_plant"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.hide()

        val tvDetailNama: TextView = findViewById(R.id.tv_name_detail)
        val tvDetailDesc: TextView = findViewById(R.id.tv_desc_detail)
        val imgDetailPhoto: ImageView = findViewById(R.id.iv_detail)

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
        }
    }
}