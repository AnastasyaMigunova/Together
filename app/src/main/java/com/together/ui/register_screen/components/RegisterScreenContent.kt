package com.together.ui.register_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.together.R
import com.together.ui.components.CustomButton
import com.together.ui.components.CustomDescription
import com.together.ui.components.CustomImage
import com.together.ui.components.CustomTitle
import com.together.ui.components.TextField
import com.together.ui.register_screen.RegisterState
import com.together.ui.theme.LocalCustomColors
import com.together.ui.theme.LocalCustomTypography

@Composable
fun RegisterScreenContent(
    state: RegisterState,
    navigateToAuth: () -> Unit,
    onRegistration: (String, String, String, String, String) -> Unit
) {
    val name = rememberSaveable { mutableStateOf("") }
    val surname = rememberSaveable { mutableStateOf("") }
    val phoneNumber = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LocalCustomColors.current.backgroundYellow)
            .padding(16.dp)
    ) {
        CustomImage(
            modifier = Modifier
                .padding(top = 70.dp)
                .align(Alignment.CenterHorizontally)
        )
        CustomTitle(
            modifier = Modifier
                .padding(top = 58.dp),
            text = stringResource(id = R.string.register_title)
        )
        CustomDescription(
            modifier = Modifier
                .padding(top = 6.dp),
            text = stringResource(id = R.string.register_description)
        )
        TextField(text = name, hintResId = R.string.name)
        TextField(text = surname, hintResId = R.string.surname)
        TextField(text = phoneNumber, hintResId = R.string.phone_number)
        TextField(text = password, hintResId = R.string.password)
        Spacer(modifier = Modifier.weight(1f))
        CustomButton(
            text = stringResource(id = R.string.register_title),
            isLoading = state.isLoading
        ) {
            onRegistration(
                phoneNumber.value,
                password.value,
                name.value,
                surname.value,
                ""
            )
        }
        CustomDescription(
            modifier = Modifier
                .padding(top = 12.dp, bottom = 50.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.log_with_account),
            LocalCustomTypography.current.description.copy(fontWeight = FontWeight.Bold)
        ) { navigateToAuth() }
    }
}

@Preview
@Composable
fun PreviewRegisterScreenContent() {
    RegisterScreenContent(
        state = RegisterState(
            isLoading = false,
            isSuccess = false,
            errorMessage = "Invalid credentials"
        ),
        navigateToAuth = { },
        onRegistration = { phone, password, name, surname, avatar ->
            println("Authenticating with phone: $phone, password: $password, name: $name, surname: $surname, avatar: $avatar")
        }
    )
}