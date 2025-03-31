package com.together.ui.navigation

sealed class Screen(
    val route: String
) {
    data object MainTab: Screen(MAIN_TAB)
    data object FavouritesTab: Screen(FAVOURITES_TAB)
    data object AddNoteTab: Screen(ADD_NOTE_TAB)
    data object ChatsTab: Screen(CHATS_TAB)
    data object ProfileTab: Screen(PROFILE_TAB)

    data object SplashScreen: Screen(ROUTE_SPLASH_SCREEN)
    data object AuthScreen: Screen(ROUTE_AUTH_SCREEN)
    data object RegisterScreen: Screen(ROUTE_REGISTER_SCREEN)
    data object MainScreen: Screen(ROUTE_MAIN_SCREEN)
    data object FavouritesScreen: Screen(ROUTE_FAVOURITES_SCREEN)
    data object AddNoteScreen: Screen(ROUTE_ADD_NOTE_SCREEN)
    data object ChatsScreen: Screen(ROUTE_CHATS_SCREEN)
    data object ProfileScreen: Screen(ROUTE_PROFILE_SCREEN)
    data object AllItemsScreen: Screen(ROUTE_ALL_ITEMS_SCREEN) {
        private const val ROUTE_FOR_ARGS = "show_all_page"

        fun getRouteWithArgs(type: String): String {
            return "$ROUTE_FOR_ARGS/$type"
        }
    }
    data object DetailsScreen: Screen(ROUTE_DETAILS_SCREEN) {
        private const val ROUTE_FOR_ARGS = "detail_page"

        fun getRouteWithArgs(type: String, id: String): String {
            return "$ROUTE_FOR_ARGS/$type/$id"
        }
    }
    data object ErrorScreen: Screen(ROUTE_ERROR_SCREEN)

    companion object {
        const val KEY_SHOW_ALL_TYPE = "type_of_info"
        const val KEY_ID = "id"

        private const val MAIN_TAB = "main_tab"
        private const val FAVOURITES_TAB = "favourites_tab"
        private const val ADD_NOTE_TAB = "add_note_tab"
        private const val CHATS_TAB = "chats_tab"
        private const val PROFILE_TAB = "profile_tab"

        private const val ROUTE_SPLASH_SCREEN = "splash_screen"
        private const val ROUTE_AUTH_SCREEN = "auth_screen"
        private const val ROUTE_REGISTER_SCREEN = "register_screen"
        private const val ROUTE_MAIN_SCREEN = "main_screen"
        private const val ROUTE_FAVOURITES_SCREEN = "favourites_screen"
        private const val ROUTE_ADD_NOTE_SCREEN = "add_note_screen"
        private const val ROUTE_CHATS_SCREEN = "chats_screen"
        private const val ROUTE_PROFILE_SCREEN = "profile_screen"
        private const val ROUTE_ALL_ITEMS_SCREEN = "all_items_screen/{$KEY_SHOW_ALL_TYPE}"
        private const val ROUTE_DETAILS_SCREEN = "details_screen/{$KEY_SHOW_ALL_TYPE}/{$KEY_ID}"
        private const val ROUTE_ERROR_SCREEN = "error_screen"
    }
}