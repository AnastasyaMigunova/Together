package com.together.ui.favourites_screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.together.R
import com.together.ui.favourites_screen.components.FavouritesScreenContent
import com.together.ui.main_screen.MainSideEffect
import com.together.ui.topBarParams.TopBarParams
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun FavouritesScreen(
    topBarParams: MutableState<TopBarParams>,
    viewModel: FavouritesViewModel = hiltViewModel()
) {

    val state = viewModel.collectAsState().value

    LaunchedEffect(viewModel.container.sideEffectFlow) {
        viewModel.loadLocalLastFavNote()

        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is FavouritesSideEffect.ShowError -> {
                    Log.e("FavouriteScreen", "Error: ${sideEffect.message}")
                }
            }
        }
    }
    LaunchedEffect(key1 = Unit) {
        topBarParams.value = topBarParams.value.copy(
            title = R.string.favourite,
            iconId = R.drawable.ic_search,
            height = 60.dp,
            visibility = true
        )
    }

    FavouritesScreenContent(
        state = state
    )
}