package com.l3on1kl.rick_and_morty

import com.l3on1kl.rick_and_morty.domain.model.Character
import com.l3on1kl.rick_and_morty.domain.model.Gender
import com.l3on1kl.rick_and_morty.domain.model.Status
import com.l3on1kl.rick_and_morty.presentation.character_detail.model.CharacterDetailUi
import com.l3on1kl.rick_and_morty.presentation.character_detail.model.toDetailUi
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterDetailUiMappingTest {
    @Test
    fun `maps domain model to detail ui`() {
        val domain = Character(
            id = 1,
            name = "Rick",
            status = Status.DEAD,
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

        val ui = domain.toDetailUi()

        assertEquals(domain.id, ui.id)
        assertEquals(CharacterDetailUi.Status.DEAD, ui.status)
        assertEquals(CharacterDetailUi.Gender.MALE, ui.gender)
        assertEquals(domain.episodes, ui.episodes)
    }
}
