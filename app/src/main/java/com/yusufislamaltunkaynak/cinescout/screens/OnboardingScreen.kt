package com.yusufislamaltunkaynak.cinescout.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yusufislamaltunkaynak.cinescout.R
import com.yusufislamaltunkaynak.cinescout.viewmodel.OnboardingViewModel

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    onFinish: () -> Unit,
) {
    var selectedTheme by remember { mutableStateOf<String?>(null) }
    Box(modifier = Modifier.fillMaxSize()
    ){

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        verticalArrangement = Arrangement.Center
    ) {
        Column {
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,) {
                Text(
                    text = stringResource(id = R.string.welcome_message),
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Cursive,
                    fontStyle = FontStyle.Italic,
                )
            }
            Spacer(Modifier.height(33.dp))

            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,)
            {
                Spacer(Modifier.height(20.dp))
                Text(text = stringResource(id = R.string.select_theme),
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Monospace,
                    fontStyle = FontStyle.Normal,
                    fontSize = 16.sp)
            }
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                val themes = listOf(
                    stringResource(id = R.string.light_theme),
                    stringResource(id = R.string.dark_theme),
                )

                themes.forEach { theme ->
                    Row(
                        modifier = Modifier
                            .padding(20.dp)
                            .selectable(
                                selected = selectedTheme == theme,
                                onClick = { selectedTheme = theme }
                            )
                    ) {
                        RadioButton(
                            selected = selectedTheme == theme,
                            onClick = { selectedTheme = theme },
                        )
                        Text(text = theme, modifier = Modifier.padding(start = 4.dp))
                    }
                }
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedTheme != null) Color.Black else Color.Gray,
                contentColor = Color.White
            ),
            enabled = selectedTheme != null,
            onClick = {
                viewModel.completeOnboarding()
                onFinish()

            }

        ) {
            Text(
                text = stringResource(id = R.string.start_button),
                fontWeight = FontWeight.ExtraBold,
            )
        }
    }
}
