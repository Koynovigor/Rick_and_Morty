// presentation/characters/component/placeholder/PlaceholderGrid.kt
package com.l3on1kl.rick_and_morty.presentation.characters.components.placeholder

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun PlaceholderGrid(
    modifier: Modifier = Modifier,
    showLoading: Boolean = true
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

    val widthClass = calculateWindowSizeClass(activity).widthSizeClass
    val columns = when (widthClass) {
        WindowWidthSizeClass.Compact -> 2
        WindowWidthSizeClass.Medium -> 3
        else -> 4
    }

    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(columns),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(columns * 4) {
            CharacterCardPlaceholder()
        }

        if (showLoading) {
            item(
                span = {
                    GridItemSpan(maxLineSpan)
                }
            ) {
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
