package com.together.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.profileNavGraph(
    profileScreen: @Composable () -> Unit,
    showAllItemsScreen: @Composable (type: String) -> Unit,
    detailsScreen: @Composable (type: String, id: String) -> Unit
) {
    navigation(
        startDestination = Screen.ProfileScreen.route,
        route = Screen.ProfileTab.route
    ) {

        composable(Screen.ProfileScreen.route) {
            profileScreen()
        }

        composable(
            route = Screen.AllItemsScreen.route
        ) {
            val type = it.arguments?.getString(Screen.KEY_SHOW_ALL_TYPE) ?: ""
            showAllItemsScreen(type)
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