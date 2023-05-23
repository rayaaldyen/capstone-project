package com.example.mybottomnav.ui.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mybottomnav.R
import com.example.mybottomnav.adapter.PlantAdapter
import com.example.mybottomnav.data.Plant
import com.example.mybottomnav.data.Plants
import com.example.mybottomnav.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private var list: ArrayList<Plant> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        list.addAll(Plants.getPlantData())
        showRecyclerList()
    }
    private fun showRecyclerList(){
        binding.rvPlantsResult.layoutManager = LinearLayoutManager(this)
        val plantAdapter = PlantAdapter(list)
        binding.rvPlantsResult.adapter = plantAdapter
    }
}