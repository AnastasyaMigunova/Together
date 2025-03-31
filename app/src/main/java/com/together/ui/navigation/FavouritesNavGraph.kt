package com.together.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.favouritesNavGraph(
    favouriteScreen: @Composable () -> Unit,
    detailsScreen: @Composable (type: String, id: String) -> Unit
) {
    navigation(
        startDestination = Screen.FavouritesScreen.route,
        route = Screen.FavouritesTab.route
    ) {

        composable(Screen.FavouritesScreen.route) {
            favouriteScreen()
        }

        composable(
            route = Screen.DetailsScreen.route
        ) {
            val type = it.arguments?.getString(Screen.KEY_SHOW_ALL_TYPE) ?: ""
            val id = it.arguments?.getString(Screen.KEY_ID) ?: ""
            detailsScreen(type, id)
        }
    }
}