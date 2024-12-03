import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.imcproject.ui.home.CalculateIMCViewModel
import com.google.firebase.auth.FirebaseAuth

import java.util.Date


import androidx.compose.material3.*


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width

import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.imcproject.ui.home.ProposalsActivity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculateIMCScreen(
    onBack: () -> Unit,
    viewModel: CalculateIMCViewModel = viewModel(),
    onViewProposals: @Composable () -> Unit
) {
    var age by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("Masculin") }
    var showDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var imcResult by remember { mutableStateOf(0.0) }
    var imcCategory by remember { mutableStateOf("") }
    var idealWeight by remember { mutableStateOf(0.0) }

    val context = LocalContext.current
    val currentDate = remember {
        java.text.SimpleDateFormat("dd MMMM yyyy", java.util.Locale("fr")).format(Date())
    }
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: "Invité"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calcul IMC") },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(20.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Remplissez les informations ci-dessous :",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Âge") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Âge") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = height,
                onValueChange = { height = it },
                label = { Text("Taille (cm)") },
                leadingIcon = { Icon(Icons.Default.Info, contentDescription = "Taille") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = weight,
                onValueChange = { weight = it },
                label = { Text("Poids (kg)") },
                leadingIcon = { Icon(Icons.Default.Info, contentDescription = "Poids") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text("Sexe", style = MaterialTheme.typography.labelLarge)
            Row {
                RadioButton(
                    selected = selectedGender == "Masculin",
                    onClick = { selectedGender = "Masculin" }
                )
                Text("Masculin", modifier = Modifier.padding(start = 8.dp))
                Spacer(modifier = Modifier.width(16.dp))
                RadioButton(
                    selected = selectedGender == "Féminin",
                    onClick = { selectedGender = "Féminin" }
                )
                Text("Féminin", modifier = Modifier.padding(start = 8.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Date du calcul : $currentDate",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (age.isBlank() || height.isBlank() || weight.isBlank()) {
                        errorMessage = "Veuillez remplir tous les champs avant de calculer l'IMC."
                        return@Button
                    }
                    errorMessage = ""

                    val heightInMeters = height.toFloat() / 100
                    val weightInKg = weight.toFloat()
                    imcResult = (weightInKg / (heightInMeters * heightInMeters)).toDouble()
                    imcCategory = when {
                        imcResult < 18.5 -> "Insuffisance pondérale"
                        imcResult < 24.9 -> "Poids normal"
                        imcResult < 29.9 -> "Surpoids"
                        else -> "Obésité"
                    }

                    if (imcCategory != "Poids normal") {
                        idealWeight = (22 * (heightInMeters * heightInMeters)).toDouble()
                    }

                    viewModel.calculateAndSaveIMC(
                        age = age,
                        height = height,
                        weight = weight,
                        gender = selectedGender,
                        userId = userId
                    )
                    showDialog = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text("Calculer IMC")
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Résultat du calcul IMC") },
                    text = {
                        Column {
                            Text("Âge : $age ans")
                            Text("Taille : $height cm")
                            Text("Poids : $weight kg")
                            Text("Sexe : $selectedGender")
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "IMC : ${String.format("%.2f", imcResult)}",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = if (imcCategory == "Poids normal") Color.Green else Color.Red
                            )
                            Text(
                                text = "Catégorie : $imcCategory",
                                color = if (imcCategory == "Poids normal") Color.Green else Color.Red
                            )
                            if (imcCategory != "Poids normal") {
                                Text(
                                    text = "Poids idéal : ${String.format("%.2f", idealWeight)} kg",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    color = Color.Blue
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text("Voulez-vous voir des propositions pour atteindre un IMC normal ?")
                            }
                        }
                    },
                    confirmButton = {
                        if (imcCategory != "Poids normal") {
                            Button(
                                onClick = {
                                    // Lancer ProposalsActivity
                                    val intent = Intent(context, ProposalsActivity::class.java).apply {
                                        putExtra("IMC_CATEGORY", imcCategory)
                                    }
                                    context.startActivity(intent)
                                    showDialog = false
                                }
                            ) {
                                Text("Voir les propositions")
                            }
                        }
                    },
                    dismissButton = {
                        Button(onClick = { showDialog = false }) { Text("Fermer") }
                    }
                )
            }
        }
    }
}

