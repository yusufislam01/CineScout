package com.yusufislamaltunkaynak.cinescout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

private const val SPLASH_DELAY = 2200L
@Composable
fun FirstScreen(onNext: () -> Unit, autoNavigate: Boolean = false,) {
    if (autoNavigate) {
        LaunchedEffect(Unit) {
            delay(SPLASH_DELAY)
            onNext()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onNext() }
            .background(Color.Black.copy(alpha = 0.5f)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Cine Scout",
            color = Color.White,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Cursive,
            fontSize = 78.sp,
        )
    }
}
