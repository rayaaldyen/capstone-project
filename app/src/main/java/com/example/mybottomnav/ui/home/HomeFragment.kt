package com.example.mybottomnav.ui.home

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mybottomnav.MainActivity
import com.example.mybottomnav.R
import com.example.mybottomnav.databinding.FragmentHomeBinding
import com.example.mybottomnav.dummy.adapter.ListTanamanAdapter
import com.example.mybottomnav.dummy.data.Tanaman
import com.google.android.material.color.utilities.MaterialDynamicColors.background

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvTanaman: RecyclerView
    private val list = ArrayList<Tanaman>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        (requireActivity() as MainActivity).supportActionBar!!.hide()

        playAnimation()
        rvTanaman = binding.rvPopularPlants
        rvTanaman.setHasFixedSize(true)
        list.addAll(getListTanaman())
        showRecyclerList()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("Recycle")
    private fun getListTanaman(): ArrayList<Tanaman>{
        val dataName = resources.getStringArray(R.array.data_tanaman)
        val dataPercent = resources.getStringArray(R.array.data_persen)
        val listTanaman = ArrayList<Tanaman>()
        for (i in dataName.indices){
            val tanam = Tanaman(dataName[i], dataPercent[i])
            listTanaman.add(tanam)
        }
        return listTanaman
    }

    private fun showRecyclerList(){
        rvTanaman.layoutManager = LinearLayoutManager(activity)
        val listTanamanAdapter = ListTanamanAdapter(list)
        rvTanaman.adapter = listTanamanAdapter
    }

    private fun playAnimation(){
        ObjectAnimator.ofFloat(binding.imageView, View.ALPHA, 1f).setDuration(1000).start()
        ObjectAnimator.ofFloat(binding.backgroundLayout, View.TRANSLATION_Y, 400f, -50f).setDuration(1500).start()
        val weather = ObjectAnimator.ofFloat(binding.constraintLayout, View.ALPHA, 1f).setDuration(300)
        val tvPopular = ObjectAnimator.ofFloat(binding.textView, View.ALPHA, 1f).setDuration(300)
        val popular = ObjectAnimator.ofFloat(binding.constraintLayout2, View.ALPHA, 1f).setDuration(300)

        AnimatorSet().apply {
            playSequentially(weather, tvPopular, popular)
            start()
        }
    }
}