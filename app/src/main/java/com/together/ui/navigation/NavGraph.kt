package com.together.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

private const val TRANSITION_DURATION = 300

@Composable
fun NavGraph(
    navHostController: NavHostController,
    splashScreenContent: @Composable () -> Unit,
    registerScreenContent: @Composable () -> Unit,
    authScreenContent: @Composable () -> Unit,
    mainScreenContent: @Composable () -> Unit,
    favouritesScreenContent: @Composable () -> Unit,
    addNoteScreenContent: @Composable () -> Unit,
    chatsScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.SplashScreen.route,
        enterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
        exitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popExitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popEnterTransition = { fadeIn(tween(TRANSITION_DURATION)) }
    ) {
        composable(Screen.SplashScreen.route) {
            splashScreenContent()
        }
        composable(Screen.RegisterScreen.route) {
            registerScreenContent()
        }
        composable(Screen.AuthScreen.route) {
            authScreenContent()
        }
        composable(Screen.MainScreen.route) {
            mainScreenContent()
        }
        composable(Screen.FavouritesScreen.route) {
            favouritesScreenContent()
        }
        composable(Screen.AddNoteScreen.route) {
            addNoteScreenContent()
        }
        composable(Screen.ChatsScreen.route) {
            chatsScreenContent()
        }
        composable(Screen.ProfileScreen.route) {
            profileScreenContent()
        }
    }
}