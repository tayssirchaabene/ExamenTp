package com.example.imcproject.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier



@Composable
fun TrackingScreen(viewModel: TrackingViewModel) {
    val recommendations by viewModel.recommendations.collectAsState()
    val weightState by viewModel.weightState.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Suivi quotidien", style = MaterialTheme.typography.headlineMedium)

        recommendations.forEach { rec ->
            Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Text(rec.text, style = MaterialTheme.typography.bodyMedium)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = weightState,
            onValueChange = { viewModel.updateWeight(it) },
            label = { Text("Poids actuel") }
        )

        Button(onClick = { viewModel.submitWeight() }) {
            Text("Soumettre")
        }
    }
}
