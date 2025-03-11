package com.together.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.together.ui.theme.LocalCustomColors

@Composable
fun CustomCourseCard(
    modifier: Modifier = Modifier,
    title: String,
    tags: List<String>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                LocalCustomColors.current.backgroundYellow,
                RoundedCornerShape(8.dp)
            )
            .clickable {}
            .padding(16.dp),
    ) {
        CustomTitle(
            modifier = Modifier
                .width(240.dp),
            text = title
        )

        CustomChipGroup(
            modifier = Modifier
                .padding(top = 18.dp),
            tags = tags
        )
    }
}

@Preview
@Composable
fun PreviewCustomCourseCard() {
    CustomCourseCard(
        title = "Основы Андроида",
        tags = listOf(
            "View",
            "Компоненты андроид",
            "Создание проекта",
            "Intent",
            "Manifest"
        )
    )
}