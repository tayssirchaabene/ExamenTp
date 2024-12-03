package com.example.imcproject.data.repositories

import com.example.imcproject.domain.models.IMCResult

interface IMCRepository {
    suspend fun saveIMCData(
        userId: String,
        age: String,
        height: String,
        weight: String,
        gender: String,
        imcResult: IMCResult
    )
}
