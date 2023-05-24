package com.example.mybottomnav.ui.add

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
//        val addViewModel =
//            ViewModelProvider(this).get(AddViewModel::class.java)

        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.predictButton.setOnClickListener {
            startActivity(Intent(activity, ResultActivity::class.java))
        }
        return root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}