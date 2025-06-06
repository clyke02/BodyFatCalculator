package edu.unikom.bodyfatpercentagecalculator.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BodyFatData(
    val beratBadan: Double,
    val tinggiBadan: Double,
    val usia: Int,
    val jenisKelamin: JenisKelamin
) : Parcelable

enum class JenisKelamin {
    LAKI_LAKI,
    PEREMPUAN
} 