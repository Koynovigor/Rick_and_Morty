package com.l3on1kl.rick_and_morty.presentation.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.l3on1kl.rick_and_morty.R
import com.l3on1kl.rick_and_morty.presentation.screens.main.placeholder.PlaceholderGrid
import com.l3on1kl.rick_and_morty.presentation.util.ErrorMapper
import com.l3on1kl.rick_and_morty.ui.util.UiText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    modifier: Modifier = Modifier,
    viewModel: CharacterViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val errorMapper = remember {
        ErrorMapper()
    }
    val notifications = remember {
        SnackbarHostState()
    }
    val characters = viewModel.characters.collectAsLazyPagingItems()
    var isRefreshing by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(
        characters.loadState.refresh
    ) {
        if (characters.loadState.refresh !is LoadState.Loading) {
            isRefreshing = false
        }
    }

    val refreshError = characters.loadState.refresh as? LoadState.Error
    val appendError = characters.loadState.append as? LoadState.Error

    LaunchedEffect(
        refreshError,
        appendError
    ) {
        val error = refreshError?.error ?: appendError?.error

        if (error != null && characters.itemCount > 0) {
            notifications.showSnackbar(
                errorMapper.map(
                    error
                ).asString(context)
            )
        }
    }

    PullToRefreshBox(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing),
        isRefreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            characters.refresh()
        },
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            when {
                characters.itemCount > 0 -> {
                    CharacterGrid(characters)
                }

                characters.loadState.refresh is LoadState.Loading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }

                characters.loadState.refresh is LoadState.Error -> {
                    PlaceholderGrid()
                    LaunchedEffect(refreshError) {
                        notifications.showSnackbar(
                            errorMapper.map(
                                refreshError!!.error
                            ).asString(context)
                        )
                    }
                }

                else -> {
                    Text(
                        text = UiText.Resource(R.string.empty_list).asString(context),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            SnackbarHost(
                hostState = notifications,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}
