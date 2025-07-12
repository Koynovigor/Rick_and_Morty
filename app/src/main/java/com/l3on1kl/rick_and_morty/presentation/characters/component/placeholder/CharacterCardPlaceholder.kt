// presentation/characters/component/placeholder/CharacterCardPlaceholder.kt
package com.l3on1kl.rick_and_morty.presentation.characters.component.placeholder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.placeholder
import androidx.wear.compose.material3.rememberPlaceholderState

@Composable
fun CharacterCardPlaceholder(
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(28.dp)
    val phState = rememberPlaceholderState(isVisible = true)
    Card(
        modifier = modifier
            .aspectRatio(0.75f),
        shape = shape,
        elevation = CardDefaults.elevatedCardElevation(6.dp)
    ) {
        Box(Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .placeholder(
                        placeholderState = phState,
                        shape = shape,
                        color = MaterialTheme.colorScheme.surfaceContainer
                    )
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black.copy(
                                    alpha = 0.75f
                                )
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(
                        horizontal = 14.dp,
                        vertical = 12.dp
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(20.dp)
                        .placeholder(
                            phState,
                            color = MaterialTheme.colorScheme.surfaceContainer.copy(0.5f)
                        )
                )
                Spacer(Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(14.dp)
                        .placeholder(
                            phState,
                            color = MaterialTheme.colorScheme.surfaceContainer.copy(0.5f)
                        )
                )
            }
        }
    }
}
