package com.example.imcproject.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imcproject.data.repositories.IMCRepository
import com.example.imcproject.domain.usecases.CalculateIMCUseCase

class CalculateIMCViewModelFactory(
    private val calculateIMCUseCase: CalculateIMCUseCase,
    private val imcRepository: IMCRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalculateIMCViewModel::class.java)) {
            return CalculateIMCViewModel(calculateIMCUseCase, imcRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
