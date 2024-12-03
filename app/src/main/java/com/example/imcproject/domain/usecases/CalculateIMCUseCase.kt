package com.example.imcproject.domain.usecases

import com.example.imcproject.domain.models.IMCResult

class CalculateIMCUseCase {
    operator fun invoke(weight: Double, height: Double): IMCResult {
        val imc = weight / (height * height)
        val category = when {
            imc < 16 -> "Sévèrement insuffisant"
            imc < 18.5 -> "Insuffisant"
            imc < 25 -> "Normal"
            imc < 30 -> "Surpoids"
            imc < 35 -> "Obèse classe 1"
            imc < 40 -> "Obèse classe 2"
            else -> "Obèse classe 3"
        }
        return IMCResult(imc, category)
    }
}

