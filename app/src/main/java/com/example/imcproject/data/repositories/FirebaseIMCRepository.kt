package com.example.imcproject.data.repositories

import com.example.imcproject.domain.models.IMCResult
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseIMCRepository(
    private val firebaseDb: FirebaseFirestore
) : IMCRepository {
    override suspend fun saveIMCData(
        userId: String,
        age: String,
        height: String,
        weight: String,
        gender: String,
        imcResult: IMCResult
    ) {
        val data = hashMapOf(
            "userId" to userId,
            "age" to age,
            "height" to height,
            "weight" to weight,
            "gender" to gender,
            "imcValue" to imcResult.value,
            "category" to imcResult.category,
            "timestamp" to System.currentTimeMillis()
        )
        firebaseDb.collection("historiqueIMC").add(data).await()
    }
}
