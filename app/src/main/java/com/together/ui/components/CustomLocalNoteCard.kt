package com.together.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.together.R
import com.together.ui.models.LocalNoteVO
import com.together.ui.theme.LocalCustomColors
import com.together.ui.theme.LocalCustomTypography
import com.together.ui.theme.textGray

@Composable
fun CustomLocalNoteCard(
    modifier: Modifier = Modifier,
    localNoteVO: LocalNoteVO?
) {
    val customTypography = LocalCustomTypography.current
    val customColors = LocalCustomColors.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
            .clickable {}
    ) {
        if (localNoteVO != null) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        LocalCustomColors.current.backgroundYellow,
                        RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                CustomTitle(text = localNoteVO.title)
                Text(
                    text = localNoteVO.content[0].text,
                    modifier = Modifier.fillMaxWidth(),
                    style = customTypography.description,
                    color = customColors.cardTextDescription,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(y = (-12).dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(textGray)
                    .padding(vertical = 8.dp, horizontal = 12.dp)
            ) {
                CustomDescription(
                    text = localNoteVO.date,
                    style = customTypography.textHint.copy(color = Color.White)
                ) {}
            }
        } else {
            Text(
                text = stringResource(id = R.string.no_local_notes),
                modifier = Modifier
                    .align(Alignment.Center),
                style = customTypography.description
            )
        }
    }
}