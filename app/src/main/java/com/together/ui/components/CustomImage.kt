package com.together.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.together.R

@Composable
fun CustomImage(
    modifier: Modifier = Modifier,
    image: Int = R.drawable.ic_logo
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "Logo",
        modifier = modifier

    )
}