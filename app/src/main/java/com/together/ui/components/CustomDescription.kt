package com.together.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.together.ui.theme.LocalCustomColors
import com.together.ui.theme.LocalCustomTypography

@Composable
fun CustomDescription(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = LocalCustomTypography.current.description,
    onClick: (() -> Unit)? = null
) {
    Text(
        text = text,
        modifier = modifier
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(
                            bounded = false,
                            radius = 24.dp,
                            color = LocalCustomColors.current.backgroundInputField
                        )
                    ) { onClick() }
                } else {
                    Modifier
                }
            ),
        style = style
    )
}

@Preview
@Composable
fun CustomDescriptionPreview() {
    CustomDescription(text = "Пожалуйста, войдите для продолжения") {}
}