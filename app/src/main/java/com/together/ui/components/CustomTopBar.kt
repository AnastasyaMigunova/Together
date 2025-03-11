package com.together.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.together.R
import com.together.ui.theme.LocalCustomColors
import com.together.ui.theme.LocalCustomTypography

@Composable
fun CustomTopBar(
    modifier: Modifier = Modifier,
    @StringRes textId: Int,
    @DrawableRes iconId: Int,
    height: Dp,
    onBackClick: (() -> Unit)?,
    onIconClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
            .height(height)
            .background(LocalCustomColors.current.backgroundYellow)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onBackClick != null) {
            Icon(
                modifier = Modifier
                    .clip(CircleShape)
                    .padding(end = 16.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(
                            bounded = false,
                            radius = 24.dp,
                            color = LocalCustomColors.current.backgroundInputField
                        )
                    ) { onBackClick() }
                    .align(Alignment.CenterVertically),
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = null,
                tint = Color.Unspecified,
            )
        }

        Text(
            text = stringResource(id = textId),
            style = LocalCustomTypography.current.topBarTitle
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = "search icon",
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable(
                    enabled = true,
                    onClick = { onIconClick() }
                )
                .background(
                    if (iconId != R.drawable.ic_users) {
                        LocalCustomColors.current.backgroundInputField
                    } else {
                        Color.Transparent
                    }
                )
                .padding(10.dp)
        )
    }
}

@Preview
@Composable
fun PreviewCustomTopBar() {
    CustomTopBar(
        textId = R.string.main_title,
        iconId = R.drawable.ic_search,
        height = 60.dp,
        onBackClick = {}
    ) {}
}