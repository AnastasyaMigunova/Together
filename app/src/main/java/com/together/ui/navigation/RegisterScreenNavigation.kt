package com.together.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.together.ui.navigation.controllers.navigateToHomeTab
import com.together.ui.register_screen.RegisterScreen

const val REGISTER_SCREEN_ROUTE = "register_screen"
private const val TRANSITION_DURATION = 300

fun NavController.navigateToRegisterScreen() {
    this.navigate(REGISTER_SCREEN_ROUTE) {
        popUpTo(REGISTER_SCREEN_ROUTE) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

fun NavGraphBuilder.registerScreen(
    navController: NavController
) {
    composable(
        route = REGISTER_SCREEN_ROUTE,
        enterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
        exitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popExitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popEnterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
    ) {
        RegisterScreen(
            navigateToAuth = { navController.navigateToAuthScreen() },
            navigateToMain = { navController.navigateToHomeTab() }
        )
    }
}