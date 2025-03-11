package com.together.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.together.ui.auth_screen.AuthScreen
import com.together.ui.navigation.controllers.navigateToHomeTab
import com.together.ui.navigation.controllers.navigateToHomeTabClearStack

const val AUTH_SCREEN_ROUTE = "auth_screen"
private const val TRANSITION_DURATION = 300

fun NavController.navigateToAuthScreen() {
    this.navigate(AUTH_SCREEN_ROUTE) {
        popUpTo(AUTH_SCREEN_ROUTE) {
            inclusive = true
        }
        launchSingleTop = true
    }
}
fun NavController.navigateToAuthScreenClearStack() {
    this.navigate(AUTH_SCREEN_ROUTE) {
        popUpTo(0) {
            inclusive = true
        }
        launchSingleTop = true
    }
}


fun NavGraphBuilder.authScreen(
    navController: NavController
) {
    composable(
        route = AUTH_SCREEN_ROUTE,
        enterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
        exitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popExitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popEnterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
    ) {
        AuthScreen(
            navigateToRegister = { navController.navigateToRegisterScreen() },
            navigateToMain = { navController.navigateToHomeTabClearStack() }
        )
    }
}