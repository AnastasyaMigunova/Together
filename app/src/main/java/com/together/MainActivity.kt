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
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.together.ui.components.CustomBottomBar
import com.together.ui.course_details_screen.DetailsScreen
import com.together.ui.error_screen.ErrorScreen
import com.together.ui.favourites_screen.FavouritesScreen
import com.together.ui.main_screen.MainScreen
import com.together.ui.navigation.NavGraph
import com.together.ui.navigation.NavigationItem
import com.together.ui.navigation.Screen
import com.together.ui.navigation.rememberNavigationState
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
                val navigationState = rememberNavigationState()
                val isBottomBarVisible = remember { mutableStateOf(true) }

                Scaffold(
                    containerColor = Color.Transparent,
                    modifier = Modifier
                        .padding(
                            bottom = WindowInsets.navigationBars.asPaddingValues()
                                .calculateBottomPadding()
                        ),
                    bottomBar = {
                        val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

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
                                    navBackStackEntry?.destination?.hierarchy?.any {
                                        it.route == navItem.screen.route
                                    } ?: false
                                }
                            ) {
                                navigationState.navigateTo(it.screen.route)
                            }
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavGraph(
                            navHostController = navigationState.navHostController,
                            splashScreenContent = {
                                SplashScreen(
                                    navigateToAuth = { navigationState.navigateAndClearTo(Screen.AuthScreen.route) },
                                    navigateToMain = { navigationState.navigateAndClearTo(Screen.MainScreen.route) }
                                )
                            },
                            registerScreenContent = {
                                RegisterScreen(
                                    navigateToAuth = { navigationState.navigateTo(Screen.AuthScreen.route) },
                                    navigateToMain = { navigationState.navigateAndClearTo(Screen.MainScreen.route) },
                                )
                            },
                            authScreenContent = {
                                RegisterScreen(
                                    navigateToAuth = { navigationState.navigateTo(Screen.AuthScreen.route) },
                                    navigateToMain = { navigationState.navigateAndClearTo(Screen.MainScreen.route) }
                                )
                            },
                            mainScreenContent = {
                                MainScreen(
                                    navigateToError = { navigationState.navigateTo(Screen.ErrorScreen.route) },
                                    navigateToAllItems = { }
                                )
                            },
                            favouritesScreenContent = {
                                FavouritesScreen()
                            },
                            addNoteScreenContent = { },
                            chatsScreenContent = { },
                            profileScreenContent = {
                                ProfileScreen(
                                    navigateToError = {  }
                                )
                             },
                            allItemsScreenContent = { },
                            detailsScreenContent = { type, id ->
                                DetailsScreen(
                                    type = type,
                                    id = id
                                )
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