package com.together.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.addNoteNavGraph(
    addNoteScreen: @Composable () -> Unit
) {
    navigation(
        startDestination = Screen.AddNoteScreen.route,
        route = Screen.AddNoteTab.route
    ) {

        composable(Screen.AddNoteScreen.route) {
            addNoteScreen()
        }
    }
}