package com.example.mybottomnav.ui.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mybottomnav.databinding.FragmentAccountBinding
import com.example.mybottomnav.ui.login.LoginActivity

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private lateinit var accountViewModel: AccountViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupViewModel()
        logoutAction()
        return root

    }

    private fun setupViewModel() {
        accountViewModel = ViewModelProvider(
            this
        )[AccountViewModel::class.java]

            binding.tvUsername.text = accountViewModel.getUser().username
            binding.tvEmail.text = accountViewModel.getUser().email
        }
    private fun logoutAction() {
        binding.logoutButton.setOnClickListener {
            accountViewModel.logout()
            Intent(activity, LoginActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}