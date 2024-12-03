package com.example.imcproject.ui.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imcproject.R

@OptIn(ExperimentalMaterial3Api::class)
class ProposalsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imcCategory = intent.getStringExtra("IMC_CATEGORY") ?: "Normale"
        setContent {
            ProposalsScreen(imcCategory = imcCategory)
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun ProposalsScreen(imcCategory: String) {
    val recommendations = mapOf(
        "Sous-poids" to listOf(
            Recommendation("Consommez des aliments riches en protéines (viandes, légumineuses, œufs).", R.drawable.protein),
            Recommendation("Augmentez votre apport calorique avec des fruits secs et des huiles saines.", R.drawable.calories),
            Recommendation("Pratiquez des exercices légers pour stimuler votre appétit.", R.drawable.exercise)
        ),
        "Surpoids" to listOf(
            Recommendation("Réduisez les aliments riches en sucres et graisses saturées.", R.drawable.sugar),
            Recommendation("Augmentez vos activités physiques, comme le jogging ou la natation.", R.drawable.jogging),
            Recommendation("Fixez-vous des objectifs réalistes pour perdre du poids.", R.drawable.goals)
        ),
        "Obésité" to listOf(
            Recommendation("Adoptez une alimentation saine avec l'aide d'un nutritionniste.", R.drawable.nutritionnist),
            Recommendation("Pratiquez des sports modérés adaptés à votre condition physique.", R.drawable.sports),
            Recommendation("Recherchez un soutien psychologique pour rester motivé.", R.drawable.support)
        )
    )

    var showNotificationAlert by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Propositions pour $imcCategory") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            item {
                Text(
                    text = "Propositions pour atteindre un IMC normal",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            recommendations[imcCategory]?.forEach { recommendation ->
                item {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = recommendation.imageRes),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(64.dp)
                                    .padding(end = 16.dp)
                            )
                            Text(
                                text = recommendation.text,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Voulez-vous un suivi quotidien et des propositions quotidiennes pour atteindre votre objectif ?",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                Button(
                    onClick = { showNotificationAlert = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Activer les notifications quotidiennes")
                }
            }
        }

        if (showNotificationAlert) {
            AlertDialog(
                onDismissRequest = { showNotificationAlert = false },
                title = { Text("Notification Activée") },
                text = { Text("Les notifications quotidiennes ont été activées avec succès.") },
                confirmButton = {
                    Button(onClick = { showNotificationAlert = false }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}
