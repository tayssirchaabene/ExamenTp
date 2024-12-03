package com.example.imcproject.data.repositories

interface AuthRepository {
    suspend fun registerUser(email: String, password: String, fullName: String): Result<String>
    suspend fun loginUser(email: String, password: String): Result<String>
}
