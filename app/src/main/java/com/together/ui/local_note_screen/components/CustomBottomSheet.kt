package com.together.ui.local_note_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.together.R
import com.together.ui.theme.CustomTypography
import com.together.ui.theme.LocalCustomTypography

@Composable
fun CustomBottomSheet(onDismiss: () -> Unit) {
    val customTypography = LocalCustomTypography.current
    Column(
        modifier = Modifier
            .background(Color.White)
            .height(140.dp)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.what_add),
            style = customTypography.bigDescription.copy(
                fontWeight = FontWeight.Bold
            )
        )

        CustomRow(
            iconId = R.drawable.ic_add_text,
            textId = R.string.add_text,
            customTypography = customTypography
        )
        CustomRow(
            iconId = R.drawable.ic_add_image,
            textId = R.string.add_image,
            customTypography = customTypography
        )
    }
}

@Composable
fun CustomRow(
    iconId: Int,
    textId: Int,
    customTypography: CustomTypography
) {
    Row(
        modifier = Modifier
            .padding(top = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = ""
        )
        Text(
            text = stringResource(id = textId),
            modifier = Modifier.padding(start = 12.dp),
            style = customTypography.description
        )
    }
}

@Preview
@Composable
fun PreviewCustomBottomSheet() {
    CustomBottomSheet {}
}