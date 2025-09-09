package com.yusufislamaltunkaynak.cinescout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yusufislamaltunkaynak.cinescout.model.Movies
import com.yusufislamaltunkaynak.cinescout.viewmodel.MovieViewModel





/*@Composable
fun HomeScreen(viewModel: MovieViewModel = androidx.hilt.navigation.compose.hiltViewModel(),
               onNext: () -> Unit) {
    val movies by viewModel.movieList
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onNext() } // Ekrana basınca diğer ekrana geç
            .background(Color.Black.copy(alpha = 0.8f)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cine Scout",
            color = Color.White,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Cursive,
            fontSize = 62.sp,
        )
        MovieList(movieList = movies)
    }
}*/

@Composable
fun MovieList(movieList : List<Movies>){
    LazyColumn(modifier = Modifier.fillMaxSize()
        .background(color = Color.White)){
        items(movieList) {
        Text(text = it.title,
            color = Color.Black)
            MovieRow(movie = it)
        }
    }
}

@Composable
fun MovieRow(movie: Movies){
    Column(
        modifier = Modifier.fillMaxWidth()
            .border(BorderStroke(8.dp, Color.Gray ))
            .clickable{
            }
            .background(color = MaterialTheme.colorScheme.inversePrimary)
            .padding(10.dp)

    ){
    Column {
        Text(movie.original_language,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(2.dp),
            fontWeight = FontWeight.ExtraBold)
        
        Text(movie.overview,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(2.dp),
            fontWeight = FontWeight.ExtraBold)

        Text(movie.original_title,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(2.dp),
            fontWeight = FontWeight.ExtraBold)
    }
    }
}



