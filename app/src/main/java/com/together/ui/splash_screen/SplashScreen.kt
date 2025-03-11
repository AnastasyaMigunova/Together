package com.together.ui.splash_screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.together.ui.splash_screen.components.SplashScreenContent
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navigateToAuth: () -> Unit,
    navigateToMain: () -> Unit
) {
    val state = viewModel.collectAsState().value

    LaunchedEffect(viewModel.container.sideEffectFlow) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is SplashSideEffect.NavigateToMain -> {
                    navigateToMain()
                }

                is SplashSideEffect.NavigateToAuth -> {
                    navigateToAuth()
                }
            }
        }
    }

    SplashScreenContent()
}