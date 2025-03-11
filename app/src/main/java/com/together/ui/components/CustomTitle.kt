package com.together.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.together.ui.theme.LocalCustomTypography

@Composable
fun CustomTitle(
    modifier: Modifier = Modifier,
    text: String
    ) {
    Text(
        text = text,
        modifier = modifier,
        color = Color.Black,
        style = LocalCustomTypography.current.title
    )
}

@Preview
@Composable
fun CustomTitlePreview() {
    CustomTitle(text = "Вход")
}