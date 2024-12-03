package com.example.imcproject.domain.usecases

import com.example.imcproject.data.repositories.AuthRepository

class LoginUserUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<String> {
        return repository.loginUser(email, password)
    }
}
