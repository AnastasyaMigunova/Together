package com.together.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.together.ui.profile_screen.ProfileScreen
import com.together.ui.topBarParams.TopBarParams

const val PROFILE_SCREEN_ROUTE = "profile_screen"
private const val TRANSITION_DURATION = 300

fun NavController.navigateToProfileScreen() {
    this.navigate(PROFILE_SCREEN_ROUTE)
}

fun NavGraphBuilder.profileScreen(
    navController: NavController,
    topBarParams: MutableState<TopBarParams>
) {
    composable(
        route = PROFILE_SCREEN_ROUTE,
        enterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
        exitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popExitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popEnterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
    ) {
        ProfileScreen(
            topBarParams = topBarParams,
            navigateToError = { navController.navigateToAuthScreen() },
        )
    }
}