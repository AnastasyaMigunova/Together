package com.together.ui.show_all_items_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import com.together.R
import com.together.ui.show_all_items_screen.components.ShowAllItemsScreenContent
import com.together.ui.topBarParams.TopBarParams
import com.together.utils.ListType
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ShowAllItemsScreen(
    viewModel: ShowItemsViewModel = hiltViewModel(),
    type: String
) {
    val state = viewModel.collectAsState().value


    LaunchedEffect(viewModel.container.sideEffectFlow) {
        viewModel.loadList(type)

        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is ShowItemsSideEffect.ShowError -> {

                }
            }
        }
    }

    ShowAllItemsScreenContent(
        state = state
    )
}