package com.yusufislamaltunkaynak.cinescout.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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

    Box(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Black)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.White)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.welcome_message),
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Cursive,
                fontStyle = FontStyle.Italic,
                color = Color.White
            )

            Spacer(Modifier.height(33.dp))

            Text(
                text = stringResource(id = R.string.select_theme),
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Monospace,
                fontSize = 16.sp,
                color = Color.White
            )

            Spacer(Modifier.height(16.dp))

            val themes = listOf(
                stringResource(id = R.string.dark_theme),
                stringResource(id = R.string.light_theme),
            )

            Row {
                themes.forEach { theme ->
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .selectable(
                                selected = selectedTheme == theme,
                                onClick = { selectedTheme = theme }
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedTheme == theme,
                            onClick = { selectedTheme = theme },
                        )

                        Text(
                            text = stringResource(id = R.string.dark_theme),
                            modifier = Modifier.padding(start = 4.dp),
                            color = Color.White,
                            fontSize = 25.sp
                        )
                        Text(
                            text = stringResource(id = R.string.light_theme),
                            modifier = Modifier.padding(start = 4.dp),
                            color = Color.Black,
                            fontSize = 25.sp
                        )
                    }
                }
            }

            Spacer(Modifier.height(32.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedTheme != null) Color.Blue else Color.Gray,
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
}
