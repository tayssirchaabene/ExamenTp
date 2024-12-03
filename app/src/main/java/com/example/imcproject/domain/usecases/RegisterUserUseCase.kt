package com.example.imcproject.domain.usecases

import com.example.imcproject.data.repositories.AuthRepository

class RegisterUserUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String, fullName: String): Result<String> {
        return repository.registerUser(email, password, fullName)
    }
}
