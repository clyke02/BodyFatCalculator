package edu.unikom.bodyfatpercentagecalculator.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.unikom.bodyfatpercentagecalculator.data.model.BodyFatData
import edu.unikom.bodyfatpercentagecalculator.data.model.BodyFatResult
import edu.unikom.bodyfatpercentagecalculator.data.model.JenisKelamin
import edu.unikom.bodyfatpercentagecalculator.data.repository.BodyFatRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BodyFatViewModel @Inject constructor(
    private val repository: BodyFatRepository
) : ViewModel() {

    private val _calculationResult = MutableLiveData<BodyFatResult?>()
    val calculationResult: LiveData<BodyFatResult?> = _calculationResult

    private val _inputValidation = MutableLiveData<String?>()
    val inputValidation: LiveData<String?> = _inputValidation

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun calculateBodyFat(
        beratBadan: String,
        tinggiBadan: String,
        usia: String,
        jenisKelamin: JenisKelamin
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            
            val validationResult = validateInput(beratBadan, tinggiBadan, usia)
            if (validationResult != null) {
                _inputValidation.value = validationResult
                _isLoading.value = false
                return@launch
            }

            try {
                val bodyFatData = BodyFatData(
                    beratBadan = beratBadan.toDouble(),
                    tinggiBadan = tinggiBadan.toDouble(),
                    usia = usia.toInt(),
                    jenisKelamin = jenisKelamin
                )

                val result = repository.calculateBodyFat(bodyFatData)
                _calculationResult.value = result
                _inputValidation.value = null

            } catch (e: Exception) {
                _inputValidation.value = "Terjadi kesalahan dalam perhitungan"
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun validateInput(beratBadan: String, tinggiBadan: String, usia: String): String? {
        return when {
            beratBadan.isBlank() -> "Berat badan harus diisi"
            tinggiBadan.isBlank() -> "Tinggi badan harus diisi"
            usia.isBlank() -> "Usia harus diisi"
            beratBadan.toDoubleOrNull() == null -> "Berat badan harus berupa angka"
            tinggiBadan.toDoubleOrNull() == null -> "Tinggi badan harus berupa angka"
            usia.toIntOrNull() == null -> "Usia harus berupa angka"
            beratBadan.toDouble() <= 0 -> "Berat badan harus lebih dari 0"
            tinggiBadan.toDouble() <= 0 -> "Tinggi badan harus lebih dari 0"
            usia.toInt() <= 0 -> "Usia harus lebih dari 0"
            beratBadan.toDouble() > 500 -> "Berat badan tidak valid"
            tinggiBadan.toDouble() > 300 -> "Tinggi badan tidak valid"
            usia.toInt() > 150 -> "Usia tidak valid"
            else -> null
        }
    }

    fun clearResult() {
        _calculationResult.value = null
        _inputValidation.value = null
    }
} 