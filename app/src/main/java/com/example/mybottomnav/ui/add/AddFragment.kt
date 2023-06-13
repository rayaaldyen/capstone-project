package com.example.mybottomnav.ui.add

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mybottomnav.databinding.FragmentAddBinding
import com.example.mybottomnav.ui.recommendation.RecommendationActivity

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
        binding.constraintLayout1.setOnClickListener {
            startActivity(Intent(activity, RecommendationActivity::class.java))
        }

        return root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("Recycle")
    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.ALPHA, 1f).setDuration(1000).start()

        ObjectAnimator.ofFloat(binding.backgroundLayout, View.TRANSLATION_Y, 400f, -50f)
            .setDuration(1000).start()
        val recom =
            ObjectAnimator.ofFloat(binding.constraintLayout1, View.ALPHA, 1f).setDuration(300)
        val predict =
            ObjectAnimator.ofFloat(binding.constraintLayout2, View.ALPHA, 1f).setDuration(300)
        AnimatorSet().apply {
            playSequentially(recom, predict)
            start()
        }

    }
}