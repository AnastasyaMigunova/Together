package com.together.ui.auth_screen.components

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
import com.together.ui.auth_screen.AuthState
import com.together.ui.components.CustomButton
import com.together.ui.components.CustomDescription
import com.together.ui.components.CustomImage
import com.together.ui.components.CustomTitle
import com.together.ui.components.TextField
import com.together.ui.theme.LocalCustomColors
import com.together.ui.theme.LocalCustomTypography
import com.together.ui.theme.textGray

@Composable
fun AuthScreenContent(
    state: AuthState,
    navigateToRegister: () -> Unit,
    onAuthenticate: (String, String) -> Unit
) {
    val customTypography = LocalCustomTypography.current
    val customColors = LocalCustomColors.current
    val phoneNumber = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(customColors.backgroundYellow)
            .padding(horizontal = 16.dp)
    ) {
        CustomImage(
            modifier = Modifier
                .padding(top = 70.dp)
                .align(Alignment.CenterHorizontally)
        )
        CustomTitle(
            modifier = Modifier
                .padding(top = 70.dp),
            text = stringResource(id = R.string.auth_title)
        )
        CustomDescription(
            text = stringResource(id = R.string.auth_description),
            modifier = Modifier
                .padding(top = 6.dp),
            style = customTypography.description.copy(
                color = textGray
            )
        )
        TextField(text = phoneNumber, hintResId = R.string.phone_number)
        TextField(text = password, hintResId = R.string.password)
        Spacer(modifier = Modifier.weight(1f))

        CustomButton(
            text = stringResource(id = R.string.text_enter),
            isLoading = state.isLoading,
        ) {
            onAuthenticate(phoneNumber.value, password.value)
        }

        CustomDescription(
            text = stringResource(id = R.string.register_title),
            modifier = Modifier
                .padding(top = 12.dp, bottom = 50.dp)
                .align(Alignment.CenterHorizontally),
            style = customTypography.description.copy(fontWeight = FontWeight.Bold)
        ) { navigateToRegister() }
    }
}

@Preview
@Composable
fun PreviewAuthScreenContent() {
    AuthScreenContent(
        state = AuthState(
            isLoading = false,
            isSuccess = true,
            errorMessage = null
        ),
        navigateToRegister = { },
        onAuthenticate = { phoneNumber, password ->
            println("Authenticating with phone: $phoneNumber, password: $password")
        }
    )
}