package com.together.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.together.ui.models.CommunityNoteVO
import com.together.ui.theme.LocalCustomColors
import com.together.ui.theme.LocalCustomTypography
import com.together.ui.theme.textGray

@Composable
fun CustomCommunityNoteCard(
    modifier: Modifier = Modifier,
    communityNoteVO: CommunityNoteVO
) {
    val customTypography = LocalCustomTypography.current
    val customColors = LocalCustomColors.current

    Box(
        modifier = modifier
            .padding(top = 12.dp)
            .clickable {}
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    LocalCustomColors.current.backgroundYellow,
                    RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 12.dp)
                .padding(top = 26.dp)
                .padding(bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CustomTitle(text = communityNoteVO.title)
            Text(
                text = communityNoteVO.content[0].text,
                modifier = Modifier.fillMaxWidth(),
                style = customTypography.description,
                color = customColors.cardTextDescription,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(y = (-12).dp)
                .clip(RoundedCornerShape(8.dp))
                .background(textGray)
                .padding(vertical = 8.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CustomAvatar(
                modifier = Modifier
                    .size(14.dp),
                avatar = communityNoteVO.author.avatar
            )
            CustomDescription(
                text = "${communityNoteVO.author.name} ${communityNoteVO.author.surname}",
                style = customTypography.textHint.copy(color = Color.White)
            ) {}
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
                text = communityNoteVO.date,
                style = customTypography.textHint.copy(color = Color.White)
            ) {}
        }
    }
}