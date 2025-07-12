package com.l3on1kl.rick_and_morty.presentation.character_detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.l3on1kl.rick_and_morty.presentation.character_detail.model.CharacterDetailUiState

@Composable
fun ErrorState(
    modifier: Modifier = Modifier,
    state: CharacterDetailUiState.Error,
) {
    Box(
        modifier.fillMaxSize(),
        Alignment.Center
    ) {
        Text(
            state.message.asString(LocalContext.current)
        )
    }
}
