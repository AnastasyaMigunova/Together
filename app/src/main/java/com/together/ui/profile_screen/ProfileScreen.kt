package com.together.ui.profile_screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.together.R
import com.together.ui.profile_screen.components.ProfileScreenContent
import com.together.ui.topBarParams.TopBarParams
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ProfileScreen(
    topBarParams: MutableState<TopBarParams>,
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToError: () -> Unit
) {
    val state = viewModel.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.loadProfile()

        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is ProfileSideEffect.NavigateToError -> {
                    Log.e("ProfileScreen", "Error: ${sideEffect.message}")
                    navigateToError()
                }
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        topBarParams.value = topBarParams.value.copy(
            title = R.string.profile,
            iconId = R.drawable.ic_users,
            height = 60.dp
        )
    }

    ProfileScreenContent(
        state = state
    )
}