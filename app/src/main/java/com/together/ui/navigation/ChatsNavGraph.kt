package com.together.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.chatsNavGraph(
    chatScreen: @Composable () -> Unit
) {
    navigation(
        startDestination = Screen.ChatsScreen.route,
        route = Screen.ChatsTab.route
    ) {

        composable(Screen.ChatsScreen.route) {
            chatScreen()
        }
    }
}