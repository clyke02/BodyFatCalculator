package edu.unikom.bodyfatpercentagecalculator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dagger.hilt.android.AndroidEntryPoint
import edu.unikom.bodyfatpercentagecalculator.data.model.JenisKelamin
import edu.unikom.bodyfatpercentagecalculator.databinding.ActivityMainBinding
import edu.unikom.bodyfatpercentagecalculator.ui.viewmodel.BodyFatViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: BodyFatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupWindowInsets()
        setupUI()
        observeViewModel()
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupUI() {
        binding.btnCalculate.setOnClickListener {
            val beratBadan = binding.etBeratBadan.text.toString()
            val tinggiBadan = binding.etTinggiBadan.text.toString()
            val usia = binding.etUsia.text.toString()
            val jenisKelamin = if (binding.rbLakiLaki.isChecked) {
                JenisKelamin.LAKI_LAKI
            } else {
                JenisKelamin.PEREMPUAN
            }

            viewModel.calculateBodyFat(beratBadan, tinggiBadan, usia, jenisKelamin)
        }
    }

    private fun observeViewModel() {
        viewModel.calculationResult.observe(this) { result ->
            if (result != null) {
                // Navigasi ke ResultActivity
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("RESULT_DATA", result)
                startActivity(intent)
                
                // Clear result setelah navigasi
                viewModel.clearResult()
            }
        }

        viewModel.inputValidation.observe(this) { errorMessage ->
            if (errorMessage != null) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.btnCalculate.isEnabled = !isLoading
        }
    }
}