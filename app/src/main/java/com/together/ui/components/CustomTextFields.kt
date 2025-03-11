package com.together.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.together.ui.theme.LocalCustomColors
import com.together.ui.theme.LocalCustomTypography
import com.together.ui.theme.textGray

@Composable
fun TextField(
    text: MutableState<String>,
    @StringRes hintResId: Int = 0,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val customTypography = LocalCustomTypography.current
    val customColors = LocalCustomColors.current
    Box(
        modifier = Modifier
            .padding(top = 12.dp)
            .fillMaxWidth()
            .background(customColors.backgroundInputField, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        BasicTextField(
            value = text.value,
            onValueChange = { newValue ->
                text.value = newValue
            },
            modifier = Modifier
                .fillMaxWidth(),
            enabled = true,
            keyboardOptions = keyboardOptions,
            textStyle = customTypography.textHint,
            decorationBox = { innerTextField ->
                if (text.value.isEmpty()) {
                    Text(
                        text = stringResource(id = hintResId),
                        style = customTypography.textHint.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = textGray
                    )
                }
                innerTextField()
            })
    }
}

@Preview
@Composable
fun PreviewInputFieldInfo() {
    val textState: MutableState<String> = remember { mutableStateOf("Номер телефона") }

    TextField(text = textState)
}