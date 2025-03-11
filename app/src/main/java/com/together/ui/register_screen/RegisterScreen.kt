package com.together.ui.register_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.together.ui.register_screen.components.RegisterScreenContent
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    navigateToAuth: () -> Unit,
    navigateToMain: () -> Unit
) {
    val state = viewModel.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(viewModel.container.sideEffectFlow) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is RegisterSideEffect.NavigateToMain -> {
                    navigateToMain()
                }

                is RegisterSideEffect.ShowError -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                    Log.e("RegisterScreen", "Error: ${sideEffect.message}")
                }
            }
        }
    }

    RegisterScreenContent(
        state = state,
        navigateToAuth = navigateToAuth,
        onRegistration = { phone, password, name, surname, avatar ->
            viewModel.registration(
                phoneNumber = phone,
                password = password,
                name = name,
                surname = surname,
                avatar = avatar
            )
        }
    )
}