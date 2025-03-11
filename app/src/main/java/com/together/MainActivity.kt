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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.together.ui.components.CustomBottomBar
import com.together.ui.components.CustomTopBar
import com.together.ui.components.FAVOURITES_TAB
import com.together.ui.components.HOME_TAB
import com.together.ui.components.PROFILE_TAB
import com.together.ui.navigation.NavHost
import com.together.ui.navigation.SPLASH_SCREEN_ROUTE
import com.together.ui.navigation.controllers.navigateToFavouritesTab
import com.together.ui.navigation.controllers.navigateToHomeTab
import com.together.ui.navigation.controllers.navigateToProfileTab
import com.together.ui.theme.TogetherTheme
import com.together.ui.topBarParams.TopBarParams
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TogetherTheme {
                val isBottomBarVisible = remember { mutableStateOf(false) }
                val topBarParams = remember {
                    mutableStateOf(
                        TopBarParams(
                            title = R.string.main_title,
                            iconId = R.drawable.ic_search,
                            height = 60.dp,
                            titleItemDetails = null,
                            courseDescription = null,
                            noteDate = null,
                            visibility = false,
                            onBackClick = null
                        )
                    )
                }
                val selectedTab = rememberSaveable { mutableIntStateOf(HOME_TAB) }
                val navController = rememberNavController()

                Scaffold(
                    topBar = {
                        AnimatedVisibility(
                            visible = topBarParams.value.visibility,
                            enter = fadeIn() + expandVertically(),
                            exit = fadeOut() + shrinkVertically(),
                        ) {
                            CustomTopBar(
                                textId = topBarParams.value.title,
                                iconId = topBarParams.value.iconId,
                                height = topBarParams.value.height,
                                onBackClick = topBarParams.value.onBackClick
                            ) {}
                        }
                    },
                    bottomBar = {
                        AnimatedVisibility(
                            visible = isBottomBarVisible.value,
                            enter = fadeIn() + expandVertically(),
                            exit = fadeOut() + shrinkVertically(),
                        ) {
                            CustomBottomBar(
                                selectedTab = selectedTab.intValue
                            ) { tab ->
                                selectedTab.intValue = tab
                                when (tab) {
                                    HOME_TAB -> {
                                        navController.navigateToHomeTab()
                                    }

                                    FAVOURITES_TAB -> {
                                        navController.navigateToFavouritesTab()
                                    }

                                    PROFILE_TAB -> {
                                        navController.navigateToProfileTab()
                                    }
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(
                            navController = navController,
                            startDestination = SPLASH_SCREEN_ROUTE,
                            topBarParams = topBarParams,
                            setBottomBarVisibility = { isBottomBarVisible.value = it }
                        )
                    }
                }
            }
        }
    }
}