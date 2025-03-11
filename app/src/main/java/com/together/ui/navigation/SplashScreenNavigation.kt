package com.together.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.together.ui.navigation.controllers.navigateToHomeTabClearStack
import com.together.ui.splash_screen.SplashScreen

const val SPLASH_SCREEN_ROUTE = "splash_screen"
private const val TRANSITION_DURATION = 300

fun NavGraphBuilder.splashScreen(
    navController: NavController
) {
    composable(
        route = SPLASH_SCREEN_ROUTE,
        enterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
        exitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popExitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popEnterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
    ) {
        SplashScreen(
            navigateToAuth = { navController.navigateToAuthScreenClearStack() },
            navigateToMain = { navController.navigateToHomeTabClearStack() }
        )
    }
}