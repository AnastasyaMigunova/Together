package com.together.ui.main_screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.together.ui.main_screen.components.MainScreenContent
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun MainScreen(
    navigateToError: () -> Unit,
    navigateToAllItems: (type: String) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState().value

    LaunchedEffect(viewModel.container.sideEffectFlow) {
        viewModel.loadData()

        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is MainSideEffect.NavigateTo -> {
                    navigateToAllItems(sideEffect.type)
                }

                is MainSideEffect.NavigateToError -> {
                    Log.e("MainScreen", "Error: ${sideEffect.message}")
                    navigateToError()
                }
            }
        }
    }

    MainScreenContent(
        state = state,
        navigateToAllItems = { type -> viewModel.navigate(type) }
    )
}