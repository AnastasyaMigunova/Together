package com.together.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.together.ui.navigation.controllers.favouritesTabHost
import com.together.ui.navigation.controllers.homeTabHost
import com.together.ui.navigation.controllers.profileTabHost
import com.together.ui.topBarParams.TopBarParams

@Composable
fun NavHost(
    navController: NavHostController,
    startDestination: String,
    topBarParams: MutableState<TopBarParams>,
    setBottomBarVisibility: (Boolean) -> Unit
) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        splashScreen(navController)
        registerScreen(navController)
        authScreen(navController)

        homeTabHost(topBarParams, setBottomBarVisibility)
        profileTabHost(topBarParams)
        favouritesTabHost(topBarParams)
    }
}