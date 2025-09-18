package com.yusufislamaltunkaynak.cinescout

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yusufislamaltunkaynak.cinescout.screens.FirstScreen
import com.yusufislamaltunkaynak.cinescout.screens.HomeScreen
import com.yusufislamaltunkaynak.cinescout.screens.OnboardingScreen
import com.yusufislamaltunkaynak.cinescout.ui.home.MovieDetailScreen
import com.yusufislamaltunkaynak.cinescout.viewmodel.MovieViewModel
import com.yusufislamaltunkaynak.cinescout.viewmodel.OnboardingViewModel

@Composable
fun AppNavigation(
    onboardingViewModel: OnboardingViewModel = hiltViewModel(),
    movieViewModel: MovieViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val isCompleted by onboardingViewModel.isOnboardingCompleted.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,

        startDestination = "first"
    ) {
        composable("first") {
            FirstScreen(
                onNext = {
                    navController.navigate("onboarding") {
                        popUpTo("first") { inclusive = true }
                    }
                },
                autoNavigate = true,
            )
        }

        composable("onboarding") {
            OnboardingScreen(
                viewModel = onboardingViewModel,
                onFinish = {
                    navController.navigate("home") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            )
        }

        composable("home") {
            HomeScreen(
                viewModel = movieViewModel,
                onMovieClick = { movie ->
                    navController.navigate("detail/${movie.id}/${movie.vote_average}/${movie.title}")
                }
            )
        }

        composable("detail/{movieId}/{voteAverage}/{title}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
            val voteAverage = backStackEntry.arguments?.getString("voteAverage")?.toDoubleOrNull()
            val title = backStackEntry.arguments?.getString("title") ?: "Başlık Yok"

            if (movieId != null && voteAverage != null) {
                MovieDetailScreen(movieId = movieId, voteAverage = voteAverage, title = title)
            } else {
                Text("Film bilgisi alınamadı")
            }
        }
    }
}
