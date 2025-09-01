package com.yusufislamaltunkaynak.cinescout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(onboardingViewModel: OnboardingViewModel = hiltViewModel()) {
    val navController = rememberNavController()

    // Flow → Compose State dönüşümü için
    val isCompleted by onboardingViewModel.isOnboardingCompleted.collectAsState(initial = false)

    NavHost(
        navController = navController,
        startDestination = if (isCompleted) "home" else "onboarding"
    ) {
        composable("onboarding") {
            OnboardingScreen(
                onFinish = {
                    // Başla butonuna basınca home ekranına git
                    navController.navigate("home") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            )
        }

        composable("home") {
            // HomeScreen placeholder, ileride implement edeceğiz
            /* HomeScreen() */
        }
    }
}
