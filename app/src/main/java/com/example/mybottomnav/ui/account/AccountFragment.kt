package com.example.mybottomnav.ui.account

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mybottomnav.ViewModelFactory
import com.example.mybottomnav.databinding.FragmentAccountBinding
import com.example.mybottomnav.model.UserPreference

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private lateinit var accountViewModel: AccountViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textAccount
//        accountViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        setupViewModel()
        logoutAction()
        return root

    }

    private fun setupViewModel() {
        accountViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(requireContext().dataStore))
        )[AccountViewModel::class.java]

        accountViewModel.getUser().observe(viewLifecycleOwner) { user ->
            binding.tvUsername.text = user.name
            binding.tvEmail.text = user.email
            binding.tvPassword.text = user.password.replace("[a-zA-Z0-9]".toRegex(), "*")
        }
    }
    private fun logoutAction() {
        binding.logoutButton.setOnClickListener {
            accountViewModel.logout()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}