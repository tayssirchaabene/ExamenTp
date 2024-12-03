package com.example.imcproject.data.datasources

import com.example.imcproject.data.repositories.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await


class FirebaseAuthDataSource : AuthRepository {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override suspend fun registerUser(email: String, password: String, fullName: String): Result<String> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user
            user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(fullName).build())?.await()
            Result.success("User registered successfully!")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun loginUser(email: String, password: String): Result<String> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.success("User logged in successfully!")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
