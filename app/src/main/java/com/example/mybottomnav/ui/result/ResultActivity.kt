package com.example.mybottomnav.ui.result

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mybottomnav.R
import com.example.mybottomnav.adapter.PlantAdapter
import com.example.mybottomnav.data.Plant
import com.example.mybottomnav.databinding.ActivityResultBinding
import com.example.mybottomnav.ui.Detail.DetailActivity
import java.sql.Array

class ResultActivity : AppCompatActivity() {

    private lateinit var rvPlant: RecyclerView
    private lateinit var binding: ActivityResultBinding
    private var list: ArrayList<Plant> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        rvPlant = findViewById(R.id.rv_plants_result)
        rvPlant.setHasFixedSize(true)

        list.addAll(getPlantData())
        showRecyclerList()
    }
    private fun showRecyclerList(){
        binding.rvPlantsResult.layoutManager = LinearLayoutManager(this)
        val plantAdapter = PlantAdapter(list)
        binding.rvPlantsResult.adapter = plantAdapter

        plantAdapter.setOnItemClickCallback(object : PlantAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Plant) {
                showSelectPlant(data)
            }
        })
    }

    private fun showSelectPlant(plant: Plant){
        val plant = Plant(plant.photoUrl, plant.nama, plant.detail, plant.category, plant.careLevel, plant.size, plant.marketTrend)
        val detailActivity = Intent(this@ResultActivity, DetailActivity::class.java)
        detailActivity.putExtra(DetailActivity.EXTRA_PLANT, plant)
        startActivity(detailActivity)
    }

    @SuppressLint("Recycle")
    private fun getPlantData(): ArrayList<Plant>{
        val dataNama = resources.getStringArray(R.array.data_result)
        val dataDesk = resources.getStringArray(R.array.data_deskripsi)
        val dataPhoto = resources.obtainTypedArray(R.array.data_result_photo)
        val dataCategory = resources.getStringArray(R.array.category)
        val dataCareLevel = resources.getStringArray(R.array.care_level)
        val dataSize = resources.getStringArray(R.array.size)
        val dataMarket = resources.getStringArray(R.array.market_prediction)
        val listPlant = ArrayList<Plant>()
        for(i in dataNama.indices){
            val plant = Plant( dataPhoto.getResourceId(i, -1), dataNama[i], dataDesk[i], dataCategory[i], dataCareLevel[i], dataSize[i], dataMarket[i])
            listPlant.add(plant)
        }
        return listPlant
    }
}