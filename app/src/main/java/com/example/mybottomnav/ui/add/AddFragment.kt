package com.example.mybottomnav.ui.add

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mybottomnav.R
import com.example.mybottomnav.databinding.FragmentAddBinding
import com.example.mybottomnav.ui.result.ResultActivity
import com.example.mybottomnav.ui.result.ResultFragment

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

    private fun playAnimation() {
        val title =
            ObjectAnimator.ofFloat(binding.predictTitle, View.ALPHA, 1f).setDuration(300)
        val textDashboard =
            ObjectAnimator.ofFloat(binding.textDashboard, View.ALPHA, 1f).setDuration(300)
        val icon =
            ObjectAnimator.ofFloat(binding.predictIcon, View.ALPHA, 1f).setDuration(300)
        val button =
            ObjectAnimator.ofFloat(binding.predictButton, View.ALPHA, 1f).setDuration(300)

        AnimatorSet().apply {
            playSequentially(title, textDashboard, icon, button)
            start()
        }

    }
}