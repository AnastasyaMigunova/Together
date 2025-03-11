package com.together.ui.components.error

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.together.R
import com.together.ui.components.CustomButton
import com.together.ui.components.CustomDescription
import com.together.ui.components.CustomImage
import com.together.ui.theme.CustomColors
import com.together.ui.theme.LocalCustomColors
import com.together.ui.theme.LocalCustomTypography

@Composable
fun ErrorScreen(
    isLoading: Boolean = false
) {
    val customTypography = LocalCustomTypography.current
    val customColors = LocalCustomColors.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CustomImage(image = R.drawable.ic_error_logo)
        CustomDescription(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 6.dp),
            text = stringResource(id = R.string.error_message_title),
            style = customTypography.bigTitle
        ) {}
        CustomDescription(
            modifier = Modifier
                .padding(bottom = 12.dp),
            text = stringResource(id = R.string.error_message_description),
            style = customTypography.bigDescription.copy(color = customColors.textDescription)
        ) {}
        CustomButton(text = stringResource(id = R.string.retry), isLoading = isLoading) {}
    }
}

@Preview
@Composable
fun PreviewErrorScreen() {
    ErrorScreen()
}