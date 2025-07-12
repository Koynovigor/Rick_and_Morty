package com.l3on1kl.rick_and_morty.presentation.character_detail.model

import com.l3on1kl.rick_and_morty.presentation.util.UiText

sealed interface CharacterDetailUiState {
    object Loading : CharacterDetailUiState

    data class Error(
        val message: UiText
    ) : CharacterDetailUiState

    data class Success(
        val data: CharacterDetailUi
    ) : CharacterDetailUiState
}
