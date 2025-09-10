package com.yusufislamaltunkaynak.cinescout.screens

import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yusufislamaltunkaynak.cinescout.model.Movies
import com.yusufislamaltunkaynak.cinescout.viewmodel.MovieViewModel

@Composable
fun HomeScreen(
    viewModel: MovieViewModel = hiltViewModel(),
    onMovieClick: (Int) -> Unit
) {
    val movies by viewModel.movies.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            error != null -> Text(
                text = "Hata: $error",
                color = Color.Red,
                modifier = Modifier.align(Alignment.Center)
            )
            movies.isNotEmpty() -> MovieList(
                movieList = movies,
                onMovieClick = onMovieClick,
                loadNextPage = { viewModel.loadNextPage() } // bu satır çok önemli
            )
            else -> Text(
                text = "Film bulunamadı",
                modifier = Modifier.align(Alignment.Center)
            )

        }
    }
}

@Composable
fun MovieList(
    movieList: List<Movies>,
    onMovieClick: (Int) -> Unit,
    loadNextPage: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(8.dp)
    ) {
        itemsIndexed(movieList) { index, movie ->
            MovieRow(
                movie = movie,
                onClick = { onMovieClick(movie.id) }
            )

            // Son item’a gelindiğinde yeni sayfa yükle
            if (index == movieList.lastIndex) {
                loadNextPage()
            }
        }
    }
}


@Composable
fun MovieRow(
    movie: Movies,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(12.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = movie.original_title,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Dil: ${movie.original_language}",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = movie.overview,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 2
        )
    }
}
