package edu.unikom.bodyfatpercentagecalculator.utils

import edu.unikom.bodyfatpercentagecalculator.data.model.BodyFatData
import edu.unikom.bodyfatpercentagecalculator.data.model.BodyFatResult
import edu.unikom.bodyfatpercentagecalculator.data.model.JenisKelamin
import edu.unikom.bodyfatpercentagecalculator.data.model.KategoriBodyFat
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.pow

@Singleton
class BodyFatCalculator @Inject constructor() {

    fun calculateBodyFat(data: BodyFatData): BodyFatResult {
        val bmi = calculateBMI(data.beratBadan, data.tinggiBadan)
        val bfp = calculateBFP(bmi, data.usia, data.jenisKelamin)
        val kategori = getKategori(bfp)

        return BodyFatResult(
            bmi = bmi,
            bfp = bfp,
            kategori = kategori,
            inputData = data
        )
    }

    private fun calculateBMI(beratBadan: Double, tinggiBadan: Double): Double {
        val tinggiMeter = tinggiBadan / 100.0
        return beratBadan / tinggiMeter.pow(2)
    }

    private fun calculateBFP(bmi: Double, usia: Int, jenisKelamin: JenisKelamin): Double {
        return when (jenisKelamin) {
            JenisKelamin.LAKI_LAKI -> (1.20 * bmi) + (0.23 * usia) - 16.2
            JenisKelamin.PEREMPUAN -> (1.20 * bmi) + (0.23 * usia) - 5.4
        }
    }

    private fun getKategori(bfp: Double): String {
        return when {
            bfp < 10 -> "${KategoriBodyFat.SANGAT_RENDAH.range} (${KategoriBodyFat.SANGAT_RENDAH.description})"
            bfp <= 20 -> "${KategoriBodyFat.RENDAH.range} (${KategoriBodyFat.RENDAH.description})"
            bfp <= 25 -> "${KategoriBodyFat.NORMAL.range} (${KategoriBodyFat.NORMAL.description})"
            bfp <= 30 -> "${KategoriBodyFat.TINGGI.range} (${KategoriBodyFat.TINGGI.description})"
            else -> "${KategoriBodyFat.SANGAT_TINGGI.range} (${KategoriBodyFat.SANGAT_TINGGI.description})"
        }
    }
} 