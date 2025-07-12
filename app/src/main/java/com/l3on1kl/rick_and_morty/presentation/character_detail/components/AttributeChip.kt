package com.l3on1kl.rick_and_morty.presentation.character_detail.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import com.l3on1kl.rick_and_morty.presentation.theme.RickAndMortyTheme

@Composable
fun AttributeChip(
    label: String,
    value: String
) {
    AssistChip(
        label = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$label: ",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = Bold
                    ),
                    maxLines = 1
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1
                )
            }
        },
        onClick = {},
        colors = AssistChipDefaults.assistChipColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(
                alpha = 0.65f
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
fun AttributeChipPreview() {
    RickAndMortyTheme {
        AttributeChip(label = "Origin", value = "Earth")
    }
}
