package com.together.ui.navigation.controllers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.together.ui.navigation.FAVOURITES_SCREEN_ROUTE
import com.together.ui.navigation.favouritesScreen
import com.together.ui.topBarParams.TopBarParams

const val FAVOURITES_TAB_ROUTE = "favourites_tab"

fun NavController.navigateToFavouritesTab() {
    this.navigate(FAVOURITES_TAB_ROUTE) {
        popUpTo(HOME_TAB_ROUTE) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavGraphBuilder.favouritesTabHost(
    topBarParams: MutableState<TopBarParams>
) {
    composable(
        route = FAVOURITES_TAB_ROUTE
    ) {
        val navController = rememberNavController()

        FavouritesTabHost(
            navController = navController,
            topBarParams = topBarParams
        )
    }
}

@Composable
private fun FavouritesTabHost(
    navController: NavHostController,
    topBarParams: MutableState<TopBarParams>
) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = FAVOURITES_SCREEN_ROUTE
    ) {
        favouritesScreen(
            navController = navController,
            topBarParams = topBarParams
        )
    }
}