package edu.unikom.bodyfatpercentagecalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.unikom.bodyfatpercentagecalculator.data.model.BodyFatResult
import edu.unikom.bodyfatpercentagecalculator.databinding.ActivityResultBinding
import java.text.DecimalFormat

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private val decimalFormat = DecimalFormat("#.##")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupWindowInsets()
        setupUI()
        displayResult()
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupUI() {
        binding.btnBack.setOnClickListener {
            finish()
        }
        
        binding.btnInfoPengembang.setOnClickListener {
            showInfoPengembangBottomSheet()
        }
    }

    private fun displayResult() {
        val result = intent.getParcelableExtra<BodyFatResult>("RESULT_DATA")
        
        result?.let {
            binding.tvResultBMI.text = decimalFormat.format(it.bmi)
            binding.tvResultBFP.text = decimalFormat.format(it.bfp)
            binding.tvResultKategori.text = it.kategori
        }
    }

    private fun showInfoPengembangBottomSheet() {
        val bottomSheet = InfoPengembangBottomSheet()
        bottomSheet.show(supportFragmentManager, "InfoPengembangBottomSheet")
    }
} 