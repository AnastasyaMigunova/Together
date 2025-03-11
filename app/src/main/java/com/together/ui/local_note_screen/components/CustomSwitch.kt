package com.together.ui.local_note_screen.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.together.R
import com.together.ui.theme.LocalCustomColors
import com.together.ui.theme.LocalCustomTypography

@Composable
fun CustomSwitch(
    modifier: Modifier = Modifier,
    onSwitch: (type: String) -> Unit
) {
    val customColors = LocalCustomColors.current
    val customTypography = LocalCustomTypography.current
    var selectedType by remember { mutableStateOf("local") }


    val localBackgroundColor by animateColorAsState(
        targetValue = if (selectedType == "local") customColors.backgroundYellow else Color.Transparent,
        animationSpec = tween(durationMillis = 300),
        label = "Local Note"
    )

    val communityBackgroundColor by animateColorAsState(
        targetValue = if (selectedType == "community") customColors.backgroundYellow else Color.Transparent,
        animationSpec = tween(durationMillis = 300),
        label = "Community Note"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(customColors.backgroundGray, RoundedCornerShape(8.dp))
            .padding(1.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            selectedType = "local"
                            onSwitch(selectedType)
                        }
                    )
                    .background(localBackgroundColor, RoundedCornerShape(8.dp))
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.local_note),
                    style = customTypography.description.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            selectedType = "community"
                            onSwitch(selectedType)
                        }
                    )
                    .background(communityBackgroundColor, RoundedCornerShape(8.dp))
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.community_note),
                    style = customTypography.description.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCustomSwitch() {
    CustomSwitch(onSwitch = {})
}