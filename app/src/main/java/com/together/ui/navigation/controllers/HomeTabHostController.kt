package com.together.ui.navigation.controllers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.together.ui.navigation.MAIN_SCREEN_ROUTE
import com.together.ui.navigation.mainScreen
import com.together.ui.navigation.showAllItemsScreen
import com.together.ui.topBarParams.TopBarParams


const val HOME_TAB_ROUTE = "home_tab"

fun NavController.navigateToHomeTab() {
    this.navigate(HOME_TAB_ROUTE) {
        popUpTo(HOME_TAB_ROUTE) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavController.navigateToHomeTabClearStack() {
    this.navigate(HOME_TAB_ROUTE) {
        popUpTo(0) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

fun NavGraphBuilder.homeTabHost(
    topBarParams: MutableState<TopBarParams>,
    setBottomBarVisibility: (Boolean) -> Unit
) {
    composable(
        route = HOME_TAB_ROUTE
    ) {
        val navController = rememberNavController()

        HomeTabHost(
            navController = navController,
            topBarParams = topBarParams,
            setBottomBarVisibility = setBottomBarVisibility
        )
    }
}

@Composable
private fun HomeTabHost(
    navController: NavHostController,
    topBarParams: MutableState<TopBarParams>,
    setBottomBarVisibility: (Boolean) -> Unit
) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = MAIN_SCREEN_ROUTE
    ) {
        mainScreen(
            navController = navController,
            topBarParams = topBarParams,
            setBottomBarVisibility = setBottomBarVisibility
        )
        showAllItemsScreen(
            navController = navController,
            topBarParams = topBarParams,

        )
    }
}