package com.example.imcproject

import CalculateIMCScreen
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import com.example.imcproject.data.repositories.FirebaseIMCRepository
import com.example.imcproject.domain.usecases.CalculateIMCUseCase

import com.example.imcproject.ui.auth.LoginScreen
import com.example.imcproject.ui.auth.RegisterScreen
import com.example.imcproject.ui.home.AboutIMCScreen

import com.example.imcproject.ui.home.CalculateIMCViewModel
import com.example.imcproject.ui.home.CalculateIMCViewModelFactory
import com.example.imcproject.ui.home.HomeScreen
import com.example.imcproject.ui.home.ProposalsActivity
import com.example.imcproject.ui.theme.IMCProjectTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore




class MainActivity : ComponentActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialisation de FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        setContent {
            val firebaseDb = FirebaseFirestore.getInstance()
            val imcRepository = FirebaseIMCRepository(firebaseDb)
            val calculateIMCUseCase = CalculateIMCUseCase()
            val viewModelFactory = CalculateIMCViewModelFactory(calculateIMCUseCase, imcRepository)
            val viewModel = ViewModelProvider(this, viewModelFactory)[CalculateIMCViewModel::class.java]

            IMCProjectTheme {
                AppNavigation(firebaseAuth)
            }
        }
    }
}


@Composable
fun AppNavigation(firebaseAuth: FirebaseAuth) {
    var currentScreen = remember { mutableStateOf("Login") } // Toujours démarrer sur Login

    when (currentScreen.value) {
        "Login" -> {
            LoginScreen(
                onLoginSuccess = { currentScreen.value = "Home" },
                onRegisterClick = { currentScreen.value = "Register" }
            )
        }
        "Register" -> {
            RegisterScreen(
                onRegisterSuccess = { currentScreen.value = "Login" },
                onLoginClick = { currentScreen.value = "Login" }
            )
        }
        "Home" -> {
            val user = firebaseAuth.currentUser
            val fullName = user?.displayName ?: "Utilisateur"
            val email = user?.email ?: "Email non disponible"

            HomeScreen(
                fullName = fullName,
                weight = "70", // Données simulées
                height = "175",
                sex = "Homme",
                previousIMCs = listOf(22.5f, 23.0f, 21.8f, 22.9f),
                onCalculateIMC = { currentScreen.value = "CalculateIMC" },
                onAboutIMC = { currentScreen.value = "AboutIMC" }
            )
        }
        "CalculateIMC" -> {
            CalculateIMCScreen(
                onBack = { currentScreen.value = "Home" },
                onViewProposals = {
                    val context = LocalContext.current
                    val intent = Intent(context, ProposalsActivity::class.java).apply {
                        val imcCategory = "Sous-poids"
                        putExtra("IMC_CATEGORY", imcCategory)
                    }
                    context.startActivity(intent)
                }
            )
        }
        "AboutIMC" -> {
            AboutIMCScreen(onBackClick = { currentScreen.value = "Home" })
        }
    }
}
