package com.l3on1kl.rick_and_morty.presentation.characters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.l3on1kl.rick_and_morty.presentation.characters.model.CharacterUi

@Composable
fun CharacterListRoute(
    modifier: Modifier = Modifier,
    viewModel: CharacterListViewModel = hiltViewModel(),
    onItemClick: (CharacterUi) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val characters = viewModel.characters.collectAsLazyPagingItems()
    val filter by viewModel.filter.collectAsStateWithLifecycle()

    CharacterListScreen(
        modifier = modifier,
        uiState = uiState,
        characters = characters,
        onSwipeRefresh = {
            characters.refresh()
        },
        onLoadError = viewModel::onLoadError,
        filter = filter,
        onFilterChange = viewModel::updateFilter,
        onItemClick = onItemClick
    )
}
