package com.example.mybottomnav.ui.result

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mybottomnav.adapter.PlantAdapter
import com.example.mybottomnav.databinding.ActivityResultBinding
import com.example.mybottomnav.ui.Detail.DetailActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private lateinit var plantAdapter: PlantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val recommendations = intent.getStringArrayExtra("RESULT")?.toList() as MutableList<String>
        showRecyclerList(recommendations)
        playAnimation()
        binding.buttonBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showRecyclerList(recommendations: List<String>) {
        plantAdapter = PlantAdapter(emptyList())
        binding.rvPlantsResult.layoutManager = LinearLayoutManager(this)
        binding.rvPlantsResult.adapter = plantAdapter
        plantAdapter.setOnItemClickCallback(object : PlantAdapter.OnItemClickCallback {
            override fun onItemClicked(data: String) {
                startActivity(Intent(this@ResultActivity, DetailActivity::class.java).apply {
                    putExtra("NAME", data)
                })
            }
        })
        if (recommendations.isNotEmpty()) {
            plantAdapter.setRecommendations(recommendations)
        } else {
            emptyRecommendation()
        }
    }

    private fun emptyRecommendation() {
        binding.emptyIcon.visibility = View.VISIBLE
        binding.tvEmpty.visibility = View.VISIBLE
        binding.textResult.visibility = View.GONE
    }

    private fun playAnimation() {

        val rvResult = ObjectAnimator.ofFloat(binding.rvPlantsResult, View.TRANSLATION_Y, 700f, 0f).setDuration(1000)
        val textResult = ObjectAnimator.ofFloat(binding.textResult, View.ALPHA, 1f).setDuration(500)
        AnimatorSet().apply {
            playSequentially(rvResult, textResult)
            start()
        }

    }
}