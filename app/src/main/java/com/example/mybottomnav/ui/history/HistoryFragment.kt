package com.example.mybottomnav.ui.history

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mybottomnav.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val historyViewModel =
            ViewModelProvider(this).get(HistoryViewModel::class.java)

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        playAnimation()
        val recycleView: RecyclerView = binding.rvPlantsHistory
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun playAnimation(){
        ObjectAnimator.ofFloat(binding.imageView2, View.ALPHA, 1f).setDuration(1000).start()
        ObjectAnimator.ofFloat(binding.pageTitle, View.ALPHA, 1f).setDuration(1000).start()
        ObjectAnimator.ofFloat(binding.backgroundLayout, View.TRANSLATION_Y, 400f, -50f).setDuration(1500).start()

        val rvHistory = ObjectAnimator.ofFloat(binding.rvPlantsHistory, View.ALPHA, 1f).setDuration(300)
        val seachBar = ObjectAnimator.ofFloat(binding.searchViewContainer, View.ALPHA, 1f).setDuration(600)

        AnimatorSet().apply {
            playSequentially(rvHistory, seachBar)
            start()
        }
    }
}