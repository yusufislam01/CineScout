package com.yusufislamaltunkaynak.cinescout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    onFinish: () -> Unit
) {
    var selectedTheme by remember { mutableStateOf("Sistem") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Hoşgeldiniz CineScout!", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(16.dp))
            Text("Uygulama temasını seçin:")

            Row {
                listOf("Sistem", "Açık", "Koyu").forEach { theme ->
                    Row(
                        Modifier
                            .padding(8.dp)
                            .selectable(
                                selected = selectedTheme == theme,
                                onClick = { selectedTheme = theme }
                            )
                    ) {
                        RadioButton(selected = selectedTheme == theme, onClick = { selectedTheme = theme })
                        Text(theme, Modifier.padding(start = 4.dp))
                    }
                }
            }
        }

        Button(
            onClick = {
                viewModel.completeOnboarding()
                onFinish()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Başla")
        }
    }
}
