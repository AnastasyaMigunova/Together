package com.together.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.together.ui.favourites_screen.FavouritesScreen
import com.together.ui.topBarParams.TopBarParams

const val FAVOURITES_SCREEN_ROUTE = "favourites_screen"
private const val TRANSITION_DURATION = 300

fun NavGraphBuilder.favouritesScreen(
    navController: NavController,
    topBarParams: MutableState<TopBarParams>
) {
    composable(
        route = FAVOURITES_SCREEN_ROUTE,
        enterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
        exitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popExitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popEnterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
    ) {
        FavouritesScreen(
            topBarParams = topBarParams
        )
    }
}