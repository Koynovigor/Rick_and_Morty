package com.l3on1kl.rick_and_morty.presentation.character_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.l3on1kl.rick_and_morty.R
import com.l3on1kl.rick_and_morty.presentation.character_detail.model.CharacterDetailUi
import com.l3on1kl.rick_and_morty.presentation.character_detail.model.asTextRes
import com.l3on1kl.rick_and_morty.presentation.theme.RickAndMortyTheme
import com.l3on1kl.rick_and_morty.presentation.util.DateFormatter.parseAndFormatDate

@Composable
fun AttributeCard(
    character: CharacterDetailUi
) {
    Column(
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 8.dp
        )
    ) {
        AttributeChip(
            label = stringResource(R.string.detail_species),
            value = character.species
        )

        if (character.type.isNotBlank()) {
            AttributeChip(
                stringResource(R.string.detail_type),
                character.type
            )
        }

        AttributeChip(
            stringResource(R.string.detail_gender),
            stringResource(character.gender.asTextRes())
        )

        AttributeChip(
            stringResource(R.string.detail_origin),
            character.originName
        )

        AttributeChip(
            stringResource(R.string.detail_location),
            character.locationName
        )

        AttributeChip(
            stringResource(R.string.detail_created),
            parseAndFormatDate(character.created)
        )
    }
}


@Preview(showBackground = true, name = "AttributeCard - Full Data")
@Composable
fun AttributeCardPreview() {
    val sampleCharacterFull = CharacterDetailUi(
        id = 1,
        name = "Rick Sanchez",
        status = CharacterDetailUi.Status.ALIVE,
        species = "Human",
        type = "Mad Scientist",
        gender = CharacterDetailUi.Gender.MALE,
        originName = "Earth (C-137)",
        locationName = "Citadel of Ricks",
        url = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        episodes = listOf("https://rickandmortyapi.com/api/episode/1"),
        created = "2017-11-04T18:48:46.250Z",
        imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        originUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        locationUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
    )
    RickAndMortyTheme {
        AttributeCard(character = sampleCharacterFull)
    }
}
