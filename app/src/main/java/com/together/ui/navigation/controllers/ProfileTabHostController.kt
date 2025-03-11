package com.together.ui.navigation.controllers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.together.ui.navigation.PROFILE_SCREEN_ROUTE
import com.together.ui.navigation.profileScreen
import com.together.ui.topBarParams.TopBarParams

const val PROFILE_TAB_ROUTE = "profile_tab"

fun NavController.navigateToProfileTab() {
    this.navigate(PROFILE_TAB_ROUTE) {
        popUpTo(HOME_TAB_ROUTE) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavGraphBuilder.profileTabHost(
    topBarParams: MutableState<TopBarParams>
) {
    composable(
        route = PROFILE_TAB_ROUTE,
    ) {
        val navController = rememberNavController()

        ProfileTabHost(
            navController = navController,
            topBarParams = topBarParams
        )
    }
}

@Composable
private fun ProfileTabHost(
    navController: NavHostController,
    topBarParams: MutableState<TopBarParams>
) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = PROFILE_SCREEN_ROUTE
    ) {
    }
}