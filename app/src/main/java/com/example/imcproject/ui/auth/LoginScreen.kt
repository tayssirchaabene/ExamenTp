package com.example.imcproject.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imcproject.R
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit
) {
    val firebaseAuth = FirebaseAuth.getInstance()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image d'illustration en haut avec forme arrondie
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = "Login Illustration",
            modifier = Modifier
                .size(300.dp)
                .clip(RoundedCornerShape(1.dp)) // Coins arrondis
        )

        // Titre de bienvenue
        Text(
            text = "Bienvenue ! 👋",
            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 24.sp), // Taille de police augmentée
            color = Color(0xFF007BFF), // Couleur bleue
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Text(
            text="Veuillez vous connecter pour continuer."
        )

        // Champ Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            shape = RoundedCornerShape(8.dp), // Coins arrondis
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Champ Mot de passe
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mot de passe") },
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(8.dp), // Coins arrondis
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Indicateur de chargement
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(vertical = 16.dp))
        } else {
            // Bouton de connexion
            Button(
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        isLoading = true
                        firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                isLoading = false
                                if (task.isSuccessful) {
                                    onLoginSuccess()
                                } else {
                                    errorMessage = task.exception?.localizedMessage
                                }
                            }
                    } else {
                        errorMessage = "Veuillez remplir tous les champs"
                    }
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)), // Couleur du bouton
                shape = RoundedCornerShape(8.dp) // Coins arrondis
            ) {
                Text("Se connecter", color = Color.White) // Couleur du texte
            }
        }

        // Lien pour s'inscrire
        TextButton(onClick = onRegisterClick) {
            Text("Pas encore inscrit ? Créez un compte", color = Color(0xFF007BFF)) // Couleur bleue
        }

        // Message d'erreur
        errorMessage?.let {
            Text(it, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 16.dp))
        }
    }
}
