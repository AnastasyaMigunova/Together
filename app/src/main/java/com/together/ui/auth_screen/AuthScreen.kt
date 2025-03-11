package com.together.ui.auth_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.together.ui.auth_screen.components.AuthScreenContent
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToRegister: () -> Unit,
    navigateToMain: () -> Unit
) {
    val state = viewModel.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(viewModel.container.sideEffectFlow) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is AuthSideEffect.NavigateToMain -> {
                    navigateToMain()
                }

                is AuthSideEffect.ShowError -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                    Log.e("AuthScreen", "Error: ${sideEffect.message}")
                }
            }
        }
    }

    AuthScreenContent(
        state = state,
        navigateToRegister = navigateToRegister,
        onAuthenticate = { phone, password ->
            viewModel.auth(phoneNumber = phone, password = password)
        }
    )
}