package com.together.ui.favourites_screen.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.together.R
import com.together.ui.components.CustomLocalNoteCard
import com.together.ui.error_screen.ErrorScreen
import com.together.ui.favourites_screen.FavouritesState
import com.together.ui.models.LocalNoteVO
import com.together.ui.theme.LocalCustomColors

@Composable
fun FavouritesScreenContent(
    state: FavouritesState
) {
    when {
        state.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = LocalCustomColors.current.backgroundYellow
                )
            }
        }

        state.localFavNotes.isNotEmpty() -> {
            val localFavNotes = state.localFavNotes
            SuccessLoadingData(localFavNotes)
        }

        state.localFavNotes.isEmpty() -> {
            val textEmptyList = stringResource(id = R.string.empty)

            Box(modifier = Modifier
                .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = textEmptyList)
            }
        }

        state.errorMessage != null -> {
            val error = state.errorMessage
            ErrorScreen()
            if (error != null) {
                Log.d("ERROR", error)
            }
        }
    }
}

@Composable
private fun SuccessLoadingData(
    localFavNotes: List<LocalNoteVO>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(localFavNotes) { note ->
            CustomLocalNoteCard(localNoteVO = note)
        }
    }
}

@Preview
@Composable
fun PreviewFavouritesScreenContent() {
    FavouritesScreenContent(
        state = FavouritesState(
            isLoading = false,
            localFavNotes = listOf(

            ),
            errorMessage = null
        )
    )
}