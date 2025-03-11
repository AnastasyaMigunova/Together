package com.together.ui.course_details_screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.together.R
import com.together.ui.components.CustomDescription
import com.together.ui.models.CourseVO
import com.together.ui.models.TextVO
import com.together.ui.theme.CustomColors
import com.together.ui.theme.CustomTypography
import com.together.ui.theme.LocalCustomColors
import com.together.ui.theme.LocalCustomTypography

@Composable
fun CourseDetailsScreenContent(
    courseVO: CourseVO
) {
    val customTypography = LocalCustomTypography.current
    val customColors = LocalCustomColors.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        CustomDescription(
            modifier = Modifier
                .padding(bottom = 8.dp),
            text = stringResource(id = R.string.tags),
            style = customTypography.bigDescription.copy(
                fontWeight = FontWeight.Bold
            )
        ) {}
        courseVO.tags?.let {
            BulletPointList(
                tags = it,
                colorContext = customColors,
                typographyContext = customTypography
            )
        }
        CustomDescription(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 8.dp),
            text = stringResource(id = R.string.lesson),
            style = customTypography.bigDescription.copy(
                fontWeight = FontWeight.Bold
            )
        ) {}
    }
}

@Composable
fun BulletPointList(
    tags: List<String>,
    colorContext: CustomColors,
    typographyContext: CustomTypography
) {
    Column {
        tags.forEach { tag ->
            Row(
                modifier = Modifier.padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Canvas(modifier = Modifier.size(6.dp)) {
                    drawCircle(
                        color = colorContext.textDescription,
                        radius = size.minDimension / 2
                    )
                }
                CustomDescription(
                    text = tag,
                    style = typographyContext.textHint.copy(
                        color = colorContext.textDescription
                    )
                ) {}
            }
        }
    }
}

@Preview
@Composable
fun PreviewCourseDetailsScreenContent() {
    CourseDetailsScreenContent(
        courseVO = CourseVO(
            title = "Основы Андроида",
            description = "Краткое описание или большой текст-рыба. Для этого нужно",
            tags = listOf("View", "Компоненты андроид", "Создание проекта", "Intent", "Manifest"),
            textSections = listOf(
                TextVO(
                    "",
                    "Есть список элементов. Каждый элемент из этого списка может быть с текстом, а также с картинкой. Это удобно для разделения блоков."
                )
            )
        )
    )
}