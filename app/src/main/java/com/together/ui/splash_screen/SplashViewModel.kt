package com.together.ui.splash_screen

import androidx.lifecycle.ViewModel
import com.together.domain.usecase.auth.CheckUserLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

data class SplashState(
    val isUserLoggedIn: Boolean? = null
)

sealed class SplashSideEffect {
    data object NavigateToAuth : SplashSideEffect()
    data object NavigateToMain : SplashSideEffect()
}

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkUserLoggedInUseCase: CheckUserLoggedInUseCase
) : ContainerHost<SplashState, SplashSideEffect>, ViewModel() {

    override val container = container<SplashState, SplashSideEffect>(SplashState())

    init {
        checkUserLoggedIn()
    }

    private fun checkUserLoggedIn() = intent {
        val isUserLoggedIn = checkUserLoggedInUseCase.checkUserLogged()
        if (isUserLoggedIn) {
            postSideEffect(SplashSideEffect.NavigateToMain)
        } else {
            postSideEffect(SplashSideEffect.NavigateToAuth)
        }
        reduce {
            state.copy(isUserLoggedIn = isUserLoggedIn)
        }
    }
}