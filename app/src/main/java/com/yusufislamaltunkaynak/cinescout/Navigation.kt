package com.yusufislamaltunkaynak.cinescout

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(onboardingViewModel: OnboardingViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val isCompleted by onboardingViewModel.isOnboardingCompleted.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = "first" // Başlangıç ekranı
    ) {
        composable("first") {
            FirstScreen(
                onNext = {
                    navController.navigate("onboarding") {
                        popUpTo("first") { inclusive = true }
                    }
                }, autoNavigate = true
            )
        }

        composable("onboarding") {
            OnboardingScreen(
                viewModel = onboardingViewModel,
                onFinish = {
                    navController.navigate("home") {
                        popUpTo("onboarding") { inclusive = false }
                    }
                }
            )
        }

        composable("home") {
            HomeScreen(onNext = {
                }
             )
        }
    }
}
