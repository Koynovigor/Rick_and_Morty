package com.l3on1kl.rick_and_morty.presentation.characters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.l3on1kl.rick_and_morty.R
import com.l3on1kl.rick_and_morty.domain.model.CharacterFilter
import com.l3on1kl.rick_and_morty.domain.model.Gender
import com.l3on1kl.rick_and_morty.domain.model.Status
import com.l3on1kl.rick_and_morty.presentation.characters.components.CharacterGrid
import com.l3on1kl.rick_and_morty.presentation.characters.components.EnumPicker
import com.l3on1kl.rick_and_morty.presentation.characters.components.placeholder.PlaceholderGrid
import com.l3on1kl.rick_and_morty.presentation.characters.model.CharacterListUiState
import com.l3on1kl.rick_and_morty.presentation.characters.model.CharacterUi
import com.l3on1kl.rick_and_morty.presentation.util.TextResMapper.asTextRes
import com.l3on1kl.rick_and_morty.presentation.util.UiText
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    modifier: Modifier = Modifier,
    uiState: CharacterListUiState,
    characters: LazyPagingItems<CharacterUi>,
    onSwipeRefresh: () -> Unit,
    onLoadError: (Throwable, Boolean) -> Unit,
    filter: CharacterFilter,
    onFilterChange: (CharacterFilter) -> Unit,
    onItemClick: (CharacterUi) -> Unit = {}
) {
    val gridState = rememberSaveable(
        saver = LazyGridState.Saver
    ) {
        LazyGridState()
    }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val notifications = remember {
        SnackbarHostState()
    }

    LaunchedEffect(characters.loadState) {
        listOf(
            characters.loadState.refresh,
            characters.loadState.append
        ).forEach {
            (it as? LoadState.Error)?.let { error ->
                val noCache = uiState !is CharacterListUiState.Success &&
                        uiState !is CharacterListUiState.Offline

                onLoadError(
                    error.error,
                    noCache
                )

                notifications.showSnackbar(
                    UiText.Resource(
                        R.string.no_connection
                    ).asString(context)
                )
            }
        }
    }

    val refreshAction = {
        scope.launch {
            gridState.animateScrollToItem(0)
        }

        if (uiState is CharacterListUiState.Offline ||
            uiState is CharacterListUiState.Error
        ) {
            scope.launch {
                notifications.showSnackbar(
                    UiText.Resource(
                        R.string.no_connection
                    ).asString(context)
                )
            }
        }

        onSwipeRefresh()
    }

    PullToRefreshBox(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing),
        isRefreshing = characters.loadState.refresh is LoadState.Loading &&
                filter.isEmpty,
        onRefresh = refreshAction
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(Modifier.padding(16.dp)) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = filter.name.orEmpty(),
                    onValueChange = {
                        onFilterChange(filter.copy(name = it))
                    },
                    placeholder = {
                        Text(
                            stringResource(R.string.search_hint)
                        )
                    },
                    singleLine = true
                )
                Spacer(Modifier.size(8.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = filter.species.orEmpty(),
                    onValueChange = {
                        onFilterChange(filter.copy(species = it))
                    },
                    placeholder = {
                        Text(
                            stringResource(R.string.filter_species)
                        )
                    },
                    singleLine = true
                )
                Spacer(Modifier.size(8.dp))
                Row {
                    EnumPicker(
                        modifier = Modifier.weight(1f),
                        labelRes = R.string.filter_status,
                        selected = filter.status,
                        onValueChange = {
                            onFilterChange(
                                filter.copy(status = it)
                            )
                        },
                        entries = Status.entries.toTypedArray(),
                        textResMapper = {
                            it.asTextRes()
                        }
                    )
                    Spacer(
                        modifier = Modifier.size(8.dp)
                    )
                    EnumPicker(
                        modifier = Modifier.weight(1f),
                        labelRes = R.string.filter_gender,
                        selected = filter.gender,
                        onValueChange = {
                            onFilterChange(
                                filter.copy(gender = it)
                            )
                        },
                        entries = Gender.entries.toTypedArray(),
                        textResMapper = {
                            it.asTextRes()
                        }
                    )
                }
            }
            Box(Modifier.weight(1f)) {
                when (uiState) {
                    CharacterListUiState.Loading -> {
                        PlaceholderGrid(showLoading = true)

                        CircularProgressIndicator(
                            Modifier.align(Alignment.Center)
                        )
                    }

                    is CharacterListUiState.Error -> {
                        PlaceholderGrid(showLoading = false)
                    }

                    is CharacterListUiState.Offline -> {
                        CharacterGrid(
                            modifier = Modifier.fillMaxSize(),
                            characters = characters,
                            state = gridState,
                            onItemClick = onItemClick
                        )
                    }

                    is CharacterListUiState.Success -> {
                        if (characters.itemCount == 0) {
                            Text(
                                text = stringResource(R.string.nothing_found),
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        } else {
                            CharacterGrid(
                                modifier = Modifier.fillMaxSize(),
                                characters = characters,
                                state = gridState,
                                onItemClick = onItemClick
                            )
                        }
                    }

                    CharacterListUiState.Empty -> {}
                }

                SnackbarHost(
                    notifications,
                    Modifier.align(Alignment.BottomCenter)
                ) {
                    Snackbar(
                        snackbarData = it,
                        containerColor = MaterialTheme.colorScheme.background,
                    )
                }
            }
        }
    }
}
