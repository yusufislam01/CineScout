package com.yusufislamaltunkaynak.cinescout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    onFinish: () -> Unit,
) {
    var selectedTheme by remember { mutableStateOf("Sistem") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.welcome_message),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.select_theme))

            Row {
                val themes = listOf(
                    stringResource(id = R.string.system_theme),
                    stringResource(id = R.string.light_theme),
                    stringResource(id = R.string.dark_theme)
                )

                themes.forEach { theme ->
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .selectable(
                                selected = selectedTheme == theme,
                                onClick = { selectedTheme = theme }
                            )
                    ) {
                        RadioButton(
                            selected = selectedTheme == theme,
                            onClick = { selectedTheme = theme }
                        )
                        Text(text = theme, modifier = Modifier.padding(start = 4.dp))
                    }
                }
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.completeOnboarding()
                onFinish()
            }
        ) {
            Text(text = stringResource(id = R.string.start_button))
        }
    }
}
