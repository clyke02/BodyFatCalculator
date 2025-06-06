package edu.unikom.bodyfatpercentagecalculator.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BodyFatResult(
    val bmi: Double,
    val bfp: Double,
    val kategori: String,
    val inputData: BodyFatData
) : Parcelable

enum class KategoriBodyFat(val range: String, val description: String) {
    SANGAT_RENDAH("< 10%", "Sangat Rendah"),
    RENDAH("10-20%", "Rendah"),
    NORMAL("21-25%", "Normal"),
    TINGGI("26-30%", "Tinggi"),
    SANGAT_TINGGI("> 30%", "Sangat Tinggi")
} 