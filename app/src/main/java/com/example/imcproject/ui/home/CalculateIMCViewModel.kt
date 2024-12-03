package com.example.imcproject.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imcproject.data.repositories.IMCRepository
import com.example.imcproject.domain.models.IMCResult
import com.example.imcproject.domain.usecases.CalculateIMCUseCase
import kotlinx.coroutines.launch

class CalculateIMCViewModel(
    private val calculateIMCUseCase: CalculateIMCUseCase,
    private val imcRepository: IMCRepository
) : ViewModel() {

    fun calculateAndSaveIMC(
        age: String,
        height: String,
        weight: String,
        gender: String,
        userId: String
    ) {
        val heightInMeters = height.toDouble() / 100
        val weightInKg = weight.toDouble()
        val imcResult = calculateIMCUseCase(weightInKg, heightInMeters)

        // Save data in Firebase
        viewModelScope.launch {
            imcRepository.saveIMCData(
                userId = userId,
                age = age,
                height = height,
                weight = weight,
                gender = gender,
                imcResult = imcResult
            )
        }
    }
}
