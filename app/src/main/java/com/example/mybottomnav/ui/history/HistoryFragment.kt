package com.example.mybottomnav.ui.history

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mybottomnav.R
import com.example.mybottomnav.adapter.PlantAdapter
import com.example.mybottomnav.data.Plant
import com.example.mybottomnav.databinding.FragmentHistoryBinding
import com.example.mybottomnav.ui.Detail.DetailActivity

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private var list: ArrayList<Plant> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        list.addAll(getPlantData())
        showRecyclerList()
        return root
    }
    private fun showRecyclerList(){
        binding.rvPlantsHistory.layoutManager = LinearLayoutManager(requireContext())
        val plantAdapter = PlantAdapter(list)
        binding.rvPlantsHistory.adapter = plantAdapter

        plantAdapter.setOnItemClickCallback(object : PlantAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Plant) {
                showSelectPlant(data)
            }
        })
    }

    private fun showSelectPlant(plant: Plant){
        val plant = Plant(plant.photoUrl, plant.nama, plant.detail)
        val detailActivity = Intent(activity, DetailActivity::class.java)
        detailActivity.putExtra(DetailActivity.EXTRA_PLANT, plant)
        startActivity(detailActivity)
    }

    @SuppressLint("Recycle")
    private fun getPlantData(): ArrayList<Plant>{
        val dataNama = resources.getStringArray(R.array.data_result)
        val dataDesk = resources.getStringArray(R.array.data_deskripsi)
        val dataPhoto = resources.obtainTypedArray(R.array.data_result_photo)
        val listPlant = ArrayList<Plant>()
        for(i in dataNama.indices){
            val plant = Plant( dataPhoto.getResourceId(i, -1), dataNama[i], dataDesk[i])
            listPlant.add(plant)
        }
        return listPlant
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}