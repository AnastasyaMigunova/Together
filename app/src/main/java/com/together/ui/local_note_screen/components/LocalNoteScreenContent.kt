package com.together.ui.local_note_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.together.R
import com.together.ui.components.CustomButton
import com.together.ui.theme.LocalCustomColors
import com.together.ui.theme.LocalCustomTypography
import com.together.ui.theme.textGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalNoteScreenContent() {
    val customColors = LocalCustomColors.current
    val customTypography = LocalCustomTypography.current

    val noteTitle = rememberSaveable { mutableStateOf("") }
    val noteDescription = rememberSaveable { mutableStateOf("") }

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            CustomBottomSheet {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(18.dp)
    ) {
        CustomSwitch(onSwitch = {})

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Row(
                modifier = Modifier.padding(top = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    color = customColors.backgroundGray,
                    modifier = Modifier
                        .height(32.dp)
                        .width(2.dp)
                )

                BasicTextField(
                    value = noteTitle.value,
                    onValueChange = { noteTitle.value = it },
                    textStyle = customTypography.title.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .padding(start = 14.dp)
                                .fillMaxWidth()
                        ) {
                            if (noteTitle.value.isEmpty()) {
                                Text(
                                    text = stringResource(id = R.string.note_title),
                                    style = customTypography.title.copy(
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                            innerTextField()
                        }
                    }
                )
            }
            BasicTextField(
                value = noteDescription.value,
                onValueChange = { noteDescription.value = it },
                textStyle = customTypography.description,
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        if (noteDescription.value.isEmpty()) {
                            Text(
                                text = stringResource(id = R.string.note_description),
                                style = customTypography.description.copy(
                                    color = customColors.textDescription
                                )
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(textGray)
                    .padding(10.dp)
                    .clickable { showBottomSheet = true }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_adding_note),
                    contentDescription = "Add note",
                    tint = Color.White
                )
            }

            CustomButton(
                text = stringResource(id = R.string.note_done),
                isLoading = false
            ) {}
        }
    }
}

@Preview
@Composable
fun PreviewLocalNoteScreenContent() {
    LocalNoteScreenContent()
}