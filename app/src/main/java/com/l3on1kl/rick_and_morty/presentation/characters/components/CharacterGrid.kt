package com.l3on1kl.rick_and_morty.presentation.characters.components

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.l3on1kl.rick_and_morty.presentation.characters.model.CharacterUi

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun CharacterGrid(
    modifier: Modifier = Modifier,
    characters: LazyPagingItems<CharacterUi>,
    state: LazyGridState,
    onItemClick: (CharacterUi) -> Unit = {}
) {
    val activity = LocalActivity.current
    if (activity == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }
    val windowSizeClass = calculateWindowSizeClass(activity)
    val currentWidthSizeClass = windowSizeClass.widthSizeClass


    val columns = when (currentWidthSizeClass) {
        WindowWidthSizeClass.Compact -> GridCells.Fixed(2)
        WindowWidthSizeClass.Medium -> GridCells.Fixed(3)
        else -> GridCells.Fixed(4)
    }

    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = columns,
        state = state,
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(characters.itemCount) { idx ->
            characters[idx]?.let { character ->
                CharacterCard(
                    character = character,
                    onClick = { onItemClick(character) }
                )
            }
        }
        if (characters.loadState.append is LoadState.Loading) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}
