package com.together.ui.navigation;

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.together.ui.show_all_items_screen.ShowAllItemsScreen
import com.together.ui.topBarParams.TopBarParams

const val SHOW_ALL_ITEMS_SCREEN_ROUTE = "show_all_items_screen"
private const val TYPE_KEY = "type"
private const val TRANSITION_DURATION = 300

fun NavController.navigateToShowAllItemsScreen(
    type: String?
) {
    this.navigate("$SHOW_ALL_ITEMS_SCREEN_ROUTE/$type") {
        popUpTo(SHOW_ALL_ITEMS_SCREEN_ROUTE) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavGraphBuilder.showAllItemsScreen(
    navController: NavController,
    topBarParams: MutableState<TopBarParams>
) {
    composable(
        route = "$SHOW_ALL_ITEMS_SCREEN_ROUTE/{$TYPE_KEY}",
        enterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
        exitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popExitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popEnterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
    ) { backStackEntry ->
        val argument = backStackEntry.arguments?.getString(TYPE_KEY)

        if (argument != null) {
            ShowAllItemsScreen(
                topBarParams = topBarParams,
                type = argument,
                onBackClick = navController::popBackStack
            )
        }
    }
}
