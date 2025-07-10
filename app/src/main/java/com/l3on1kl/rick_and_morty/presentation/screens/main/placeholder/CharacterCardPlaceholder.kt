package com.l3on1kl.rick_and_morty.presentation.screens.main.placeholder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.placeholder
import androidx.wear.compose.material3.rememberPlaceholderState

@Composable
fun CharacterCardPlaceholder() {
    val phState = rememberPlaceholderState(
        isVisible = true
    )

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .placeholder(
                        placeholderState = phState,
                        shape = RoundedCornerShape(8.dp)
                    )
            )

            Text(
                text = "Rick Sanchez",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
                    .placeholder(phState)
            )

            Text(
                text = "Human, ALIVE, MALE",
                modifier = Modifier
                    .padding(
                        horizontal = 8.dp,
                        vertical = 4.dp
                    )
                    .placeholder(phState)
            )
        }
    }
}
