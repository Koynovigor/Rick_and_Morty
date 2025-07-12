package com.l3on1kl.rick_and_morty

import com.l3on1kl.rick_and_morty.data.local.entity.CharacterEntity
import com.l3on1kl.rick_and_morty.data.local.entity.EntityToDomainMapper
import com.l3on1kl.rick_and_morty.domain.model.Character
import com.l3on1kl.rick_and_morty.domain.model.Gender
import com.l3on1kl.rick_and_morty.domain.model.Status
import org.junit.Assert.assertEquals
import org.junit.Test

class EntityToDomainMapperTest {
    private val mapper = EntityToDomainMapper()

    @Test
    fun `maps entity to domain`() {
        val entity = CharacterEntity(
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

        val domain = mapper(entity)

        val expected = Character(
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

        assertEquals(expected, domain)
    }
}
