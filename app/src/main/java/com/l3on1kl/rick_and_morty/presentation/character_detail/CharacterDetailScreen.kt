package com.l3on1kl.rick_and_morty.presentation.character_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.l3on1kl.rick_and_morty.R
import com.l3on1kl.rick_and_morty.presentation.character_detail.model.CharacterDetailUiState
import com.l3on1kl.rick_and_morty.presentation.util.DateFormatter.parseAndFormatDate

@Composable
fun CharacterDetailScreen(
    uiState: CharacterDetailUiState,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    when (uiState) {
        CharacterDetailUiState.Loading -> {
            CircularProgressIndicator(modifier)
        }

        is CharacterDetailUiState.Error -> {
            Text(
                text = uiState.message.asString(context),
                modifier = modifier.padding(16.dp)
            )
        }

        is CharacterDetailUiState.Success -> {
            val character = uiState.data
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = stringResource(
                        R.string.detail_status,
                        character.status
                    ),
                )
                Text(
                    text = stringResource(
                        R.string.detail_species,
                        character.species
                    ),
                )
                Text(
                    text = stringResource(
                        R.string.detail_type,
                        character.type
                    ),
                )
                Text(
                    text = stringResource(
                        R.string.detail_gender,
                        character.gender
                    ),
                )
                Text(
                    text = stringResource(
                        R.string.detail_origin,
                        character.originName
                    ),
                )
                Text(
                    text = stringResource(
                        R.string.detail_location,
                        character.locationName
                    ),
                )
                Text(
                    text = stringResource(
                        R.string.detail_episodes,
                        character.episodes.size
                    ),
                )
                Text(
                    text = stringResource(
                        R.string.detail_created,
                        parseAndFormatDate(character.created)
                    ),
                )
            }
        }
    }
}
