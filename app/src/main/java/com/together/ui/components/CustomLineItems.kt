package com.together.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.together.R
import com.together.ui.theme.LocalCustomColors
import com.together.ui.theme.LocalCustomTypography

@Composable
fun CustomLineItems(
    modifier: Modifier = Modifier,
    title: String,
    onAllClick: () -> Unit
) {
    val colorContext = LocalCustomColors.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 12.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(colorContext.backgroundGray)
    ) {
        Box(
            modifier = Modifier
                .weight(6f)
                .padding(horizontal = 12.dp, vertical = 8.dp),
        ) {
            CustomDescription(
                text = title,
                style = LocalCustomTypography.current.description.copy(fontWeight = FontWeight.Bold)
            )
        }

        Spacer(
            modifier = Modifier
                .width(2.dp)
                .height(32.dp)
                .background(Color.White)
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            CustomDescription(
                text = stringResource(id = R.string.text_all),
                style = LocalCustomTypography.current.description.copy(
                    color = colorContext.textDescription,
                    fontWeight = FontWeight.Medium
                )
            ) { onAllClick() }
        }
    }
}

@Preview
@Composable
fun PreviewCustomLineItems() {
    CustomLineItems(title = "Ваши курсы") {}
}