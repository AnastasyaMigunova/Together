package com.together.ui.register_screen

import androidx.lifecycle.ViewModel
import com.together.domain.usecase.registration.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

data class RegisterState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)

sealed class RegisterSideEffect {
    data object NavigateToMain : RegisterSideEffect()
    data class ShowError(val message: String) : RegisterSideEffect()
}

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ContainerHost<RegisterState, RegisterSideEffect>, ViewModel() {

    override val container = container<RegisterState, RegisterSideEffect>(RegisterState())

    fun registration(
        phoneNumber: String,
        password: String,
        name: String,
        surname: String,
        avatar: String
    ) = intent {
        reduce { state.copy(isLoading = true) }

        val result =
            registerUseCase.register(phoneNumber, password, name, surname, avatar)

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
        postSideEffect(RegisterSideEffect.NavigateToMain)
    }

    private fun handleFailure(exception: Throwable) = intent {
        val errorMessage = exception.message ?: "Неизвестная ошибка"
        reduce {
            state.copy(isLoading = false, isSuccess = false, errorMessage = errorMessage)
        }
        postSideEffect(RegisterSideEffect.ShowError(errorMessage))
    }
}