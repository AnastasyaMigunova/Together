package com.together.ui.topBarParams

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.unit.Dp

data class TopBarParams(
    @StringRes val title: Int,
    @DrawableRes val iconId: Int,
    val height: Dp,
    val titleItemDetails: String?,
    val courseDescription: String?,
    val noteDate: String?,
    val visibility: Boolean,
    val onIconClick: () -> Unit = {},
    val onBackClick: (() -> Unit)? = {}
)
