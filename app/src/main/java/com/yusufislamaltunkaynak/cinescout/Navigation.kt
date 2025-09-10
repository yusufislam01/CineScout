package com.yusufislamaltunkaynak.cinescout

import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yusufislamaltunkaynak.cinescout.viewmodel.MovieViewModel
import com.yusufislamaltunkaynak.cinescout.viewmodel.OnboardingViewModel
import kotlin.getValue

@Composable
fun AppNavigation(onboardingViewModel: OnboardingViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val isCompleted by onboardingViewModel.isOnboardingCompleted.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = "first",
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
                        popUpTo("onboarding") { inclusive = false }
                    }
                }
            )
        }

        composable("home") {
    
            }
        }

}