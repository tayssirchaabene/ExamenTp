package com.example.imcproject.ui.home

import androidx.compose.ui.res.painterResource
import com.example.imcproject.R

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutIMCScreen(onBackClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // Top App Bar
            TopAppBar(
                title = { Text("À propos de l'IMC") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Retour")
                    }
                }

            )

            // Header
            Text(
                text = "Comprendre de L'IMC",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            // Section Qu'est-ce que l'IMC ?
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                elevation = CardDefaults.elevatedCardElevation(4.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Qu'est-ce que l'IMC ?",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "L'Indice de Masse Corporelle (IMC) est un indicateur qui permet d'évaluer le poids par rapport à la taille d'une personne. "
                                + "Il est calculé en divisant le poids (en kg) par le carré de la taille (en m²). "
                                + "L'IMC est utilisé pour identifier les catégories de poids :",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            // Section Catégories d'IMC
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                elevation = CardDefaults.elevatedCardElevation(4.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Catégories d'IMC :",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = " - Sous-poids : IMC < 18.5\n" +
                                " - Poids normal : 18.5 ≤ IMC < 24.9\n" +
                                " - Surpoids : 25 ≤ IMC < 29.9\n" +
                                " - Obésité : IMC ≥ 30",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            // Section Image de distribution de l'IMC
            Text(
                text = "Distribution de l'IMC :",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.imc_chart), // Assurez-vous d'avoir une image avec ce nom dans le dossier res/drawable
                contentDescription = "Distribution de l'IMC",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Ajustez la taille selon vos besoins
                    .padding(bottom = 16.dp)
            )

            // Bouton pour revenir à la page précédente
            Button(onClick = onBackClick, modifier = Modifier.align(Alignment.End)) {
                Text("Retour")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutIMCScreenPreview() {
    AboutIMCScreen(onBackClick = {})
}
