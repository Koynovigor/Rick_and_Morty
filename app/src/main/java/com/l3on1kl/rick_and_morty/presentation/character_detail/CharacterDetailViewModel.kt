package com.l3on1kl.rick_and_morty.presentation.character_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l3on1kl.rick_and_morty.R
import com.l3on1kl.rick_and_morty.domain.usecase.GetCharacterUseCase
import com.l3on1kl.rick_and_morty.presentation.character_detail.model.CharacterDetailUiState
import com.l3on1kl.rick_and_morty.presentation.character_detail.model.toDetailUi
import com.l3on1kl.rick_and_morty.presentation.util.ErrorMapper
import com.l3on1kl.rick_and_morty.presentation.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharacter: GetCharacterUseCase,
    private val errorMapper: ErrorMapper
) : ViewModel() {

    private val characterId: Int = checkNotNull(savedStateHandle["id"])

    private val _uiState = MutableStateFlow<CharacterDetailUiState>(CharacterDetailUiState.Loading)
    val uiState: StateFlow<CharacterDetailUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val character = getCharacter(characterId)
            if (character != null) {
                _uiState.value = CharacterDetailUiState.Success(
                    character.toDetailUi()
                )
            } else {
                _uiState.value =
                    CharacterDetailUiState.Error(
                        UiText.Resource(R.string.unknown_error)
                    )
            }
        }
    }
}
