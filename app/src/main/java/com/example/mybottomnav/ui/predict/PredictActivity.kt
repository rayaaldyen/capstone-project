package com.example.mybottomnav.ui.predict

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.mybottomnav.R
import com.example.mybottomnav.custom.CustomButton
import com.example.mybottomnav.custom.CustomEditText
import com.example.mybottomnav.databinding.ActivityPredictBinding

class PredictActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPredictBinding
    private lateinit var predictViewModel: PredictViewModel
    private lateinit var customButton: CustomButton
    private lateinit var customEditTextKuantitas: CustomEditText
    private lateinit var customEditTextLuas: CustomEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPredictBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        customButton = binding.predictButton
        customEditTextKuantitas = binding.areaEditText
        customEditTextLuas = binding.quantityEditText


        setupViewModel()
        predictAction()
        setMyButtonEnable()
        backButtonPressed()

        customEditTextKuantitas.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { setMyButtonEnable() }
            override fun afterTextChanged(s: Editable) {}
        })
        customEditTextLuas.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { setMyButtonEnable() }
            override fun afterTextChanged(s: Editable) {}
        })

        predictViewModel.isLoading.observe(this){
            showLoading(it)
        }
        predictViewModel.predict.observe(this){
            binding.tvResult.text = it.recommendation
        }
        predictViewModel.error.observe(this){
            if (it){
                Toast.makeText(this, getString(R.string.time_out), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupViewModel(){
        predictViewModel = ViewModelProvider(
            this
        )[PredictViewModel::class.java]
    }

    private fun backButtonPressed() {
        binding.buttonBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.progressBar.bringToFront()
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setMyButtonEnable(){
        val resultKuantitas = customEditTextKuantitas.text.toString()
        val resultLuas = customEditTextLuas.text.toString()
        customButton.isEnabled = resultKuantitas.isNotEmpty() && resultLuas.isNotEmpty()
    }

    private fun predictAction(){
        binding.predictButton.setOnClickListener {
            val tanaman = binding.spinnerJenisTanaman.selectedItem.toString()
            val daerah = binding.spinnerDaerah.selectedItem.toString()
            val luas = binding.areaEditText.text.toString()
            val kuantitas = binding.quantityEditText.text.toString()
            predictViewModel.predict(tanaman, luas.toInt(), kuantitas.toInt(), daerah)
        }
    }
}