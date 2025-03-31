package com.together.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class NavigationState(
    val navHostController: NavHostController
) {

    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateAndClearTo(route: String) {
        navHostController.navigate(route) {
            popUpTo(0) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }


    fun navigateToShowAll(type: String) {
        navHostController.navigate(Screen.AllItemsScreen.getRouteWithArgs(type))
    }

    fun navigateToDetail(type: String, id: String) {
        navHostController.navigate(Screen.DetailsScreen.getRouteWithArgs(type, id))
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}