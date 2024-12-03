package com.example.imcproject.ui.home



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications


import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.imcproject.R // Assurez-vous d'avoir le bon chemin pour votre ressource.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    fullName: String,
    weight: String,
    height: String,
    sex: String,
    previousIMCs: List<Float>,
    onCalculateIMC: () -> Unit,
    onAboutIMC: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Navbar
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle, // Icône de profil
                            contentDescription = "Profil",
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Mariem Ridene", // Afficher le nom de l'utilisateur
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Action de notification */ }) {
                        Icon(
                            imageVector = Icons.Default.Notifications, // Icône de notification
                            contentDescription = "Notifications"
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )

            // Contenu principal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Ajouter une image en haut
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.imc1), // Remplacez avec votre ressource d'image
                        contentDescription = "Image Utilisateur",
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Carte des informations actuelles
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFBBDEFB)) // Couleur bleue claire
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = "Vos informations actuelles",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        Text(text = "Poids : $weight kg", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Taille : $height cm", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Sexe : $sex", style = MaterialTheme.typography.bodyLarge)
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Analyse des derniers IMC
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFCDD2)) // Couleur rouge claire
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = "Analyse des derniers IMC",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        // Composant graphique
                        AnalysisGraph(imcData = previousIMCs)
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Boutons en bas
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = onCalculateIMC,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 10.dp),
                        shape = RoundedCornerShape(50) // Bouton arrondi
                    ) {
                        Text("Calculer IMC", fontSize = 16.sp)
                    }
                    Button(
                        onClick = onAboutIMC,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 10.dp),
                        shape = RoundedCornerShape(50) // Bouton arrondi
                    ) {
                        Text("À propos d'IMC", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}
