package com.l3on1kl.rick_and_morty.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.l3on1kl.rick_and_morty.R
import com.l3on1kl.rick_and_morty.domain.model.Character
import com.l3on1kl.rick_and_morty.domain.usecase.GetCharactersUseCase
import com.l3on1kl.rick_and_morty.domain.usecase.HasCharactersUseCase
import com.l3on1kl.rick_and_morty.presentation.characters.model.CharacterListUiState
import com.l3on1kl.rick_and_morty.presentation.characters.model.CharacterUi
import com.l3on1kl.rick_and_morty.presentation.characters.model.toUi
import com.l3on1kl.rick_and_morty.presentation.util.ConnectivityObserver
import com.l3on1kl.rick_and_morty.presentation.util.ErrorMapper
import com.l3on1kl.rick_and_morty.presentation.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharacters: GetCharactersUseCase,
    hasCharacters: HasCharactersUseCase,
    connectivity: ConnectivityObserver,
    private val errorMapper: ErrorMapper,
) : ViewModel() {
    private val refreshTrigger = MutableSharedFlow<Unit>(
        replay = 0,
        extraBufferCapacity = 1
    )

    fun refresh() {
        refreshTrigger.tryEmit(Unit)
    }

    val characters: Flow<PagingData<CharacterUi>> =
        refreshTrigger
            .onStart {
                emit(Unit)
            }
            .flatMapLatest {
                getCharacters()
                    .map {
                        it.map(Character::toUi)
                    }
            }
            .cachedIn(viewModelScope)

    private val _uiState = MutableStateFlow<CharacterListUiState>(
        CharacterListUiState.Loading
    )
    val uiState: StateFlow<CharacterListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                characters,
                hasCharacters(),
                connectivity.isOnline
            ) { paging, hasCache, online ->
                when {
                    !hasCache && online -> CharacterListUiState.Loading

                    !hasCache && !online -> CharacterListUiState.Error(
                        UiText.Resource(R.string.no_connection)
                    )

                    hasCache && online -> CharacterListUiState.Success(paging)

                    else -> CharacterListUiState.Offline(paging)
                }
            }.collect(_uiState::value::set)
        }

        viewModelScope.launch {
            connectivity.isOnline
                .distinctUntilChanged()
                .filter {
                    it
                }
                .collect {
                    refresh()
                }
        }
    }

    fun onLoadError(
        error: Throwable,
        wasCacheEmpty: Boolean
    ) {
        if (wasCacheEmpty) _uiState.value = CharacterListUiState.Error(
            errorMapper.map(error)
        )
    }
}
