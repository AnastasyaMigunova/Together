package com.together.ui.navigation

import androidx.annotation.DrawableRes
import com.together.R

sealed class NavigationItem(
    val screen: Screen,
    @DrawableRes val iconId: Int
) {
    data object Main: NavigationItem(
        screen = Screen.MainScreen,
        iconId = R.drawable.ic_home
    )

    data object FavouritesNotes: NavigationItem(
        screen = Screen.FavouritesScreen,
        iconId = R.drawable.ic_favourites
    )

    data object AddNote: NavigationItem(
        screen = Screen.AddNoteScreen,
        iconId = R.drawable.ic_adding_note
    )

    data object Chats: NavigationItem(
        screen = Screen.ChatsScreen,
        iconId = R.drawable.ic_chats
    )

    data object Profile: NavigationItem(
        screen = Screen.ProfileScreen,
        iconId = R.drawable.ic_profile
    )
}