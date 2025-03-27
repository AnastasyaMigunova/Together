package com.together

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.together.ui.components.CustomBottomBar
import com.together.ui.error_screen.ErrorScreen
import com.together.ui.favourites_screen.FavouritesScreen
import com.together.ui.main_screen.MainScreen
import com.together.ui.navigation.NavGraph
import com.together.ui.navigation.NavigationItem
import com.together.ui.navigation.Screen
import com.together.ui.profile_screen.ProfileScreen
import com.together.ui.register_screen.RegisterScreen
import com.together.ui.show_all_items_screen.ShowAllItemsScreen
import com.together.ui.splash_screen.SplashScreen
import com.together.ui.theme.TogetherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TogetherTheme {
                val isBottomBarVisible = remember { mutableStateOf(true) }
                val navController = rememberNavController()

                Scaffold(
                    containerColor = Color.Transparent,
                    modifier = Modifier
                        .padding(
                            bottom = WindowInsets.navigationBars.asPaddingValues()
                                .calculateBottomPadding()
                        ),
                    bottomBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentRoute = navBackStackEntry?.destination?.route

                        val items = listOf(
                            NavigationItem.Main,
                            NavigationItem.FavouritesNotes,
                            NavigationItem.AddNote,
                            NavigationItem.Chats,
                            NavigationItem.Profile
                        )

                        AnimatedVisibility(
                            visible = isBottomBarVisible.value,
                            enter = fadeIn() + expandVertically(),
                            exit = fadeOut() + shrinkVertically(),
                        ) {
                            CustomBottomBar(
                                items = items,
                                selected = { navItem ->
                                    currentRoute == navItem.screen.route
                                }
                            ) {
                                navController.navigate(it.screen.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavGraph(
                            navHostController = navController,
                            splashScreenContent = {
                                SplashScreen(
                                    navigateToAuth = { navController.navigate(Screen.AuthScreen.route) },
                                    navigateToMain = { navController.navigate(Screen.MainScreen.route) }
                                )
                            },
                            registerScreenContent = {
                                RegisterScreen(
                                    navigateToAuth = { navController.navigate(Screen.AuthScreen.route) },
                                    navigateToMain = { navController.navigate(Screen.MainScreen.route) },
                                )
                            },
                            authScreenContent = {
                                RegisterScreen(
                                    navigateToAuth = { navController.navigate(Screen.AuthScreen.route) },
                                    navigateToMain = { navController.navigate(Screen.MainScreen.route) }
                                )
                            },
                            mainScreenContent = {
                                MainScreen(
                                    navigateToError = { navController.navigate(Screen.ErrorScreen.route) },
                                    navigateToAllItems = { navController.navigate(Screen.AllItemsScreen.route) }
                                )
                            },
                            favouritesScreenContent = {
                                FavouritesScreen()
                            },
                            addNoteScreenContent = { },
                            chatsScreenContent = { },
                            profileScreenContent = {
                                ProfileScreen(
                                    navigateToError = { navController.navigate(Screen.ErrorScreen.route) }
                                )
                             },
                            coursesScreenContent = { },
                            localNotesScreenContent = { },
                            communityNotesScreenContent = { },
                            allItemsScreenContent = { type ->
                                navController.navigate("${Screen.AllItemsScreen.route}/$type")
                            },
                            errorScreenContent = {
                                ErrorScreen()
                            }
                        )
                    }
                }
            }
        }
    }
}