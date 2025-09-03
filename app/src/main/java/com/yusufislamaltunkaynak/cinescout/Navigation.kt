package com.yusufislamaltunkaynak.cinescout

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint


@Composable
fun AppNavigation(onboardingViewModel: OnboardingViewModel = hiltViewModel()) {
    val navController = rememberNavController()

    // Flow → Compose State dönüşümü için
    val isCompleted by onboardingViewModel.isOnboardingCompleted.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = "home"
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
            FirstScreen()
        }
    }
}
