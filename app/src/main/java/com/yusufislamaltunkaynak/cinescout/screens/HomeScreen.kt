package com.yusufislamaltunkaynak.cinescout.screens

import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yusufislamaltunkaynak.cinescout.model.Movies
import com.yusufislamaltunkaynak.cinescout.viewmodel.MovieViewModel
import com.yusufislamaltunkaynak.cinescout.viewmodel.MoviesUiState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun HomeScreen(
    viewModel: MovieViewModel = hiltViewModel(),
    onMovieClick: (Movies) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is MoviesUiState.Loading -> CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )

            is MoviesUiState.Error -> Text(
                text = "Hata: ${(uiState as MoviesUiState.Error).message}",
                color = Color.Red,
                modifier = Modifier.align(Alignment.Center)
            )

            is MoviesUiState.Success -> MovieList(
                movieList = (uiState as MoviesUiState.Success).movies,
                onMovieClick = onMovieClick,
                loadNextPage = { viewModel.loadNextPage() }
            )
        }
    }
}

@Composable
fun MovieList(
    movieList: List<Movies>,
    onMovieClick: (Movies) -> Unit,
    loadNextPage: () -> Unit
) {
    val listState = rememberLazyListState()
    LaunchedEffect(listState) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collect { lastVisibleIndex ->
            if (lastVisibleIndex != null && lastVisibleIndex >= movieList.lastIndex) {
                loadNextPage()
            }
        }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(8.dp)
    ) {
        itemsIndexed(movieList, key = { _, movie -> movie.id }) { _, movie ->
            MovieRow(
                movie = movie,
                onClick = { onMovieClick(movie) }
            )
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
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.ExtraBold,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "IMDB: ${String.format("%.1f", movie.vote_average)}/10",
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = movie.overview,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 3,
            fontSize = 16.sp,

        )
    }
}
