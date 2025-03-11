package com.together.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.together.R
import com.together.ui.models.ProfileVO
import com.together.ui.theme.LocalCustomColors
import com.together.ui.theme.LocalCustomTypography
import com.together.utils.Utils.fillWidthOfParent

@Composable
fun CustomProfileCard(
    modifier: Modifier = Modifier,
    profile: ProfileVO
) {
    val customColors = LocalCustomColors.current
    val customTypography = LocalCustomTypography.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(customColors.backgroundGray)
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row {
            CustomAvatar(
                modifier = Modifier
                    .size(100.dp),
                avatar = profile.avatar
            )
            Column(
                modifier = Modifier
                    .padding(start = 20.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                CustomDescription(
                    text = profile.name,
                    style = customTypography.topBarTitle.copy(
                        color = Color.Black
                    )
                ) {}
                CustomDescription(
                    text = profile.surname,
                    style = customTypography.topBarTitle.copy(
                        color = Color.Black
                    )
                ) {}
                CustomDescription(
                    text = "Дата регистрации: ${profile.registerDate}",
                    style = customTypography.description.copy(
                        color = customColors.textDescription
                    )
                ) {}
                CustomDescription(
                    text = "Роль: ${profile.role}",
                    style = customTypography.description.copy(
                        color = customColors.textDescription
                    )
                ) {}
            }
        }
        CustomDescription(
            text = "Номер телефона: ${profile.phone}",
            style = customTypography.description.copy(
                color = customColors.blackTextDescription
            )
        ) {}

        CustomDescription(
            text = stringResource(id = R.string.text_show_number),
            style = customTypography.description.copy(
                color = customColors.blackTextDescription
            )
        ) {}
    }
}