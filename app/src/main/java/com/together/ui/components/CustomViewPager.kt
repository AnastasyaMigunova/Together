package com.together.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.together.ui.models.CourseVO
import com.together.ui.theme.LocalCustomColors
import com.together.ui.theme.textGray
import com.together.utils.Utils.fillWidthOfParent
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomViewPager(
    modifier: Modifier = Modifier,
    courses: List<CourseVO>
) {
    val customColors = LocalCustomColors.current

    Column(
        modifier = modifier
    ) {
        val pagerState = rememberPagerState { courses.size }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillWidthOfParent(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 8.dp
        ) { page ->
            Card(
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                                ).absoluteValue

                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {
                CustomCourseCard(title = courses[page].title, tags = courses[page].tags)
            }
        }

        Row(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) textGray else customColors.unselectedDot
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .background(color)
                        .height(2.dp)
                        .weight(1f)
                )
            }
        }
    }
}