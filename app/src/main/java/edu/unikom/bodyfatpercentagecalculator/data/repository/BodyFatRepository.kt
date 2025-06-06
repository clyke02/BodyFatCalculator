package edu.unikom.bodyfatpercentagecalculator.data.repository

import edu.unikom.bodyfatpercentagecalculator.data.model.BodyFatData
import edu.unikom.bodyfatpercentagecalculator.data.model.BodyFatResult
import edu.unikom.bodyfatpercentagecalculator.utils.BodyFatCalculator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BodyFatRepository @Inject constructor(
    private val calculator: BodyFatCalculator
) {
    
    fun calculateBodyFat(data: BodyFatData): BodyFatResult {
        return calculator.calculateBodyFat(data)
    }
} 