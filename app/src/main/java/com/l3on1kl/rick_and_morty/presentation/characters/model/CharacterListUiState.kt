package com.l3on1kl.rick_and_morty.presentation.characters.model

import androidx.paging.PagingData
import com.l3on1kl.rick_and_morty.presentation.util.UiText

sealed interface CharacterListUiState {
    object Loading : CharacterListUiState

    data class Error(
        val message: UiText
    ) : CharacterListUiState

    data class Success(
        val data: PagingData<CharacterUi>
    ) : CharacterListUiState

    data class Offline(
        val data: PagingData<CharacterUi>
    ) : CharacterListUiState

    object Empty : CharacterListUiState
}
