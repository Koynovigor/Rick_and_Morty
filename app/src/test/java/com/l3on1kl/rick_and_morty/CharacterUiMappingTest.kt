package com.l3on1kl.rick_and_morty

import com.l3on1kl.rick_and_morty.domain.model.Character
import com.l3on1kl.rick_and_morty.domain.model.Gender
import com.l3on1kl.rick_and_morty.domain.model.Status
import com.l3on1kl.rick_and_morty.presentation.characters.model.CharacterUi
import com.l3on1kl.rick_and_morty.presentation.characters.model.toUi
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterUiMappingTest {
    @Test
    fun `maps domain model to ui model`() {
        val domain = Character(
            id = 1,
            name = "Rick",
            status = Status.ALIVE,
            species = "Human",
            type = "",
            gender = Gender.MALE,
            imageUrl = "image",
            originName = "Earth",
            originUrl = "",
            locationName = "Earth",
            locationUrl = "",
            episodes = listOf("1"),
            url = "url",
            created = "date"
        )

        val ui = domain.toUi()

        assertEquals(domain.id, ui.id)
        assertEquals(domain.name, ui.name)
        assertEquals(CharacterUi.Status.ALIVE, ui.status)
        assertEquals(domain.species, ui.species)
        assertEquals(CharacterUi.Gender.MALE, ui.gender)
        assertEquals(domain.imageUrl, ui.imageUrl)
    }
}
