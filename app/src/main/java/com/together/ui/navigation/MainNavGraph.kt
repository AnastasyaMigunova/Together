package com.together.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.mainNavGraph(
    mainScreen: @Composable () -> Unit,
    showAllItemsScreen: @Composable (type: String) -> Unit,
    detailScreen: @Composable (type: String, id: String) -> Unit
) {
    navigation(
        startDestination = Screen.MainScreen.route,
        route = Screen.MainTab.route
    ) {

        composable(Screen.MainScreen.route) {
            mainScreen()
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
            detailScreen(type, id)
        }
    }
}