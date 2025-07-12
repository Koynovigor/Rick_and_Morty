package com.l3on1kl.rick_and_morty

import com.l3on1kl.rick_and_morty.data.local.entity.CharacterEntity
import com.l3on1kl.rick_and_morty.data.remote.mapper.DtoToEntityMapper
import com.l3on1kl.rick_and_morty.data.remote.model.CharacterDto
import com.l3on1kl.rick_and_morty.data.remote.model.LocationDto
import org.junit.Assert.assertEquals
import org.junit.Test

class DtoToEntityMapperTest {
    private val mapper = DtoToEntityMapper()

    @Test
    fun `maps dto to entity`() {
        val dto = CharacterDto(
            id = 1,
            name = "Rick",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            origin = LocationDto("Earth", ""),
            location = LocationDto("Earth", ""),
            imageUrl = "image",
            episode = listOf("1", "2"),
            url = "url",
            created = "date"
        )

        val entity = mapper(dto)

        val expected = CharacterEntity(
            id = 1,
            name = "Rick",
            status = com.l3on1kl.rick_and_morty.domain.model.Status.ALIVE,
            species = "Human",
            type = "",
            gender = com.l3on1kl.rick_and_morty.domain.model.Gender.MALE,
            imageUrl = "image",
            originName = "Earth",
            originUrl = "",
            locationName = "Earth",
            locationUrl = "",
            episodes = listOf("1", "2"),
            url = "url",
            created = "date"
        )

        assertEquals(expected, entity)
    }
}
