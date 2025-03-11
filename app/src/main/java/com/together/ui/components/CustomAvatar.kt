package com.together.ui.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.together.R

@Composable
fun CustomAvatar(
    modifier: Modifier = Modifier,
    avatar: String
) {
    AsyncImage(
        model = avatar,
        contentDescription = "Avatar",
        modifier = modifier
            .clip(CircleShape),
        error = painterResource(R.drawable.default_avatar)
    )
}