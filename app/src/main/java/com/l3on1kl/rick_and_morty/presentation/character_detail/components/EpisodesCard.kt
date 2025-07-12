package com.l3on1kl.rick_and_morty.presentation.character_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.l3on1kl.rick_and_morty.R
import com.l3on1kl.rick_and_morty.presentation.character_detail.util.DetailUtil.episodeLabel
import com.l3on1kl.rick_and_morty.presentation.theme.RickAndMortyTheme

@Composable
fun EpisodesCard(
    episodes: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            )
    ) {
        Text(
            stringResource(R.string.detail_episodes, episodes.size),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(12.dp))
        FlowRow(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            episodes.forEach { url ->
                Box(
                    modifier = Modifier
                        .padding(
                            horizontal = 4.dp
                        )
                ) {
                    AssistChip(
                        onClick = {},
                        label = {
                            Text(episodeLabel(url))
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.surface.copy(
                                alpha = 0.65f
                            )
                        )
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, name = "Episodes Card Preview")
@Composable
fun EpisodesCardPreview() {
    RickAndMortyTheme {
        val sampleEpisodes = listOf(
            "https://rickandmortyapi.com/api/episode/1",
            "https://rickandmortyapi.com/api/episode/2",
            "https://rickandmortyapi.com/api/episode/10",
            "https://rickandmortyapi.com/api/episode/25",
            "https://rickandmortyapi.com/api/episode/50"
        )
        EpisodesCard(episodes = sampleEpisodes)
    }
}
