package com.example.imcproject.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imcproject.domain.usecases.LoginUserUseCase
import com.example.imcproject.domain.usecases.RegisterUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<Result<String>?>(null)
    val authState: StateFlow<Result<String>?> = _authState

    fun registerUser(email: String, password: String, fullName: String) {
        viewModelScope.launch {
            _authState.value = registerUserUseCase(email, password, fullName)
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = loginUserUseCase(email, password)
        }
    }
}
