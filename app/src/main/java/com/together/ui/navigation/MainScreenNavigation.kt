package com.together.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.together.ui.main_screen.MainScreen
import com.together.ui.topBarParams.TopBarParams

const val MAIN_SCREEN_ROUTE = "main_screen"
private const val TRANSITION_DURATION = 300

fun NavGraphBuilder.mainScreen(
    navController: NavController,
    topBarParams: MutableState<TopBarParams>,
    setBottomBarVisibility: (Boolean) -> Unit
) {
    composable(
        route = MAIN_SCREEN_ROUTE,
        enterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
        exitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popExitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popEnterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
    ) {

        MainScreen(
            navigateToError = { navController.navigateToAuthScreen() },
            navigateToAllItems = { navController.navigateToShowAllItemsScreen(it) }
        )
    }
}