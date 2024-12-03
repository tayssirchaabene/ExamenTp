package com.example.imcproject.di



import com.example.imcproject.data.datasources.FirebaseAuthDataSource
import com.example.imcproject.data.repositories.AuthRepository
import com.example.imcproject.domain.usecases.LoginUserUseCase
import com.example.imcproject.domain.usecases.RegisterUserUseCase
import com.example.imcproject.ui.auth.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel // Import nécessaire pour les ViewModels
import org.koin.dsl.module // Import nécessaire pour définir un module

val appModule = module {
    single<AuthRepository> { FirebaseAuthDataSource() }
    factory { RegisterUserUseCase(get()) }
    factory { LoginUserUseCase(get()) }
    viewModel { AuthViewModel(get(), get()) }
}

