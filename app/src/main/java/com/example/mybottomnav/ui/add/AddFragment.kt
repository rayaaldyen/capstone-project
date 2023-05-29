package com.example.mybottomnav.ui.add

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mybottomnav.databinding.FragmentAddBinding
import com.example.mybottomnav.ui.result.ResultActivity

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val root: View = binding.root

        playAnimation()
        binding.predictButton.setOnClickListener {
            startActivity(Intent(activity, ResultActivity::class.java))
        }
        return root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun playAnimation(){
        ObjectAnimator.ofFloat(binding.imageView3, View.ALPHA, 1f).setDuration(1000).start()
        ObjectAnimator.ofFloat(binding.predictTitle, View.ALPHA, 1f).setDuration(1000).start()
        ObjectAnimator.ofFloat(binding.backgroundLayout, View.TRANSLATION_Y, 400f, -50f).setDuration(1500).start()
        val etPH = ObjectAnimator.ofFloat(binding.phEditText, View.ALPHA, 1f).setDuration(300)
        val etRain = ObjectAnimator.ofFloat(binding.rainEditText, View.ALPHA, 1f).setDuration(300)
        val etCity = ObjectAnimator.ofFloat(binding.citySelection, View.ALPHA, 1f).setDuration(300)
        val etSuhu = ObjectAnimator.ofFloat(binding.temperatureEditText, View.ALPHA, 1f).setDuration(300)
        val btnPredict = ObjectAnimator.ofFloat(binding.predictButton, View.ALPHA, 1f).setDuration(300)
        val titleInput = ObjectAnimator.ofFloat(binding.textDashboard, View.ALPHA, 1f).setDuration(600)

        AnimatorSet().apply {
            playSequentially(etPH, etRain, etCity, etSuhu, btnPredict, titleInput)
            start()
        }
    }
}