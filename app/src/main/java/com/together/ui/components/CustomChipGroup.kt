package com.together.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.together.ui.theme.LocalCustomColors
import com.together.ui.theme.LocalCustomTypography
import com.together.ui.theme.textGray

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CustomChipGroup(
    modifier: Modifier = Modifier,
    tags: List<String>
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        tags.forEach { item ->
            Item(text = item)
        }
    }
}

@Composable
fun Item(text: String) {
    Card(
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = LocalCustomColors.current.backgroundYellow),
        border = BorderStroke(1.dp, textGray)
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 4.dp),
            style = LocalCustomTypography.current.textHint,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview
@Composable
fun PreviewCustomChipGroup() {
    CustomChipGroup(
        tags = listOf(
            "View",
            "Компоненты андроид",
            "Создание проекта",
            "Intent",
            "Manifest"
        )
    )
}