package com.together.ui.navigation

sealed class Screen(
    val route: String
) {
    data object SplashScreen: Screen(ROUTE_SPLASH_SCREEN)
    data object AuthScreen: Screen(ROUTE_AUTH_SCREEN)
    data object RegisterScreen: Screen(ROUTE_REGISTER_SCREEN)
    data object MainScreen: Screen(ROUTE_MAIN_SCREEN)
    data object FavouritesScreen: Screen(ROUTE_FAVOURITES_SCREEN)
    data object AddNoteScreen: Screen(ROUTE_ADD_NOTE_SCREEN)
    data object ChatsScreen: Screen(ROUTE_CHATS_SCREEN)
    data object ProfileScreen: Screen(ROUTE_PROFILE_SCREEN)
    data object AllItemsScreen: Screen(ROUTE_ERROR_SCREEN)
    data object ErrorScreen: Screen(ROUTE_ERROR_SCREEN)

    companion object {
        private const val ROUTE_SPLASH_SCREEN = "splash_screen"
        private const val ROUTE_AUTH_SCREEN = "auth_screen"
        private const val ROUTE_REGISTER_SCREEN = "register_screen"
        private const val ROUTE_MAIN_SCREEN = "main_screen"
        private const val ROUTE_FAVOURITES_SCREEN = "favourites_screen"
        private const val ROUTE_ADD_NOTE_SCREEN = "add_note_screen"
        private const val ROUTE_CHATS_SCREEN = "chats_screen"
        private const val ROUTE_PROFILE_SCREEN = "profile_screen"
        private const val ROUTE_COURSES_SCREEN = "courses_screen"
        private const val ROUTE_LOCAL_NOTES_SCREEN = "local_notes_screen"
        private const val ROUTE_COMMUNITY_NOTES_SCREEN = "community_notes_screen"
        private const val ROUTE_ALL_ITEMS_SCREEN = "all_items_screen"
        private const val ROUTE_ERROR_SCREEN = "error_screen"
    }
}