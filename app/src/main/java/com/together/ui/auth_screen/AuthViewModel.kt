package com.together.ui.auth_screen

import androidx.lifecycle.ViewModel
import com.together.domain.usecase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

data class AuthState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)

sealed class AuthSideEffect {
    data object NavigateToMain : AuthSideEffect()
    data class ShowError(val message: String) : AuthSideEffect()
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ContainerHost<AuthState, AuthSideEffect>, ViewModel() {

    override val container = container<AuthState, AuthSideEffect>(AuthState())

    fun auth(phoneNumber: String, password: String) = intent {
        reduce { state.copy(isLoading = true) }

        val result =
            authUseCase.auth(phoneNumber, password)

        result.fold(
            onSuccess = {
                handleSuccess()
            },
            onFailure = { exception ->
                handleFailure(exception)
            }
        )
    }

    private fun handleSuccess() = intent {
        reduce {
            state.copy(isLoading = false, isSuccess = true, errorMessage = null)
        }
        postSideEffect(AuthSideEffect.NavigateToMain)
    }

    private fun handleFailure(exception: Throwable) = intent {
        val errorMessage = exception.message ?: "Неизвестная ошибка"
        reduce {
            state.copy(isLoading = false, isSuccess = false, errorMessage = errorMessage)
        }
        postSideEffect(AuthSideEffect.ShowError(errorMessage))
    }
}