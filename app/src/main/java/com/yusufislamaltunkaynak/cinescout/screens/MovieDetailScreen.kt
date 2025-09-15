package com.yusufislamaltunkaynak.cinescout.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MovieDetailScreen(movieId: Int, voteAverage: Double, title: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title ?: "Başlık Yok",
            color = Color.Black,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))


        Text(
            text = "Movie ID: $movieId",
            color = Color.Gray,
            fontSize = 23.sp
        )
        Spacer(modifier = Modifier.height(4.dp))


        Text(
            text = "IMDB: ${String.format("%.1f", voteAverage)}/10",
            color = Color.DarkGray,
            fontSize = 22.sp
        )
    }

}

