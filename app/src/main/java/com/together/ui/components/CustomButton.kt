package com.together.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.together.ui.theme.LocalCustomColors
import com.together.ui.theme.LocalCustomTypography
import com.together.ui.theme.TogetherTheme
import com.together.ui.theme.textGray

@Composable
fun CustomButton(
    text: String,
    isLoading: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                color = textGray,
            )
            .clickable(enabled = !isLoading, onClick = onClick)
            .padding(if (isLoading) 6.dp else 12.dp),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = LocalCustomColors.current.backgroundYellow,
                modifier = Modifier
                    .size(30.dp)
            )
        } else {
            Text(
                text = text,
                style = LocalCustomTypography.current.description.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun CustomButtonPreview() {
    TogetherTheme {
        CustomButton(text = "Text", isLoading = true) {}
    }
}