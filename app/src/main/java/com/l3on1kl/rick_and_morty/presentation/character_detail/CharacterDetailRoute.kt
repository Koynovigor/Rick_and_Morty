package com.l3on1kl.rick_and_morty.presentation.character_detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CharacterDetailRoute(
    avatarUrl: String?,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: CharacterDetailViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    CharacterDetailScreen(
        uiState = uiState,
        avatarUrl = avatarUrl,
        modifier = modifier,
        onBack = onBack
    )
}
