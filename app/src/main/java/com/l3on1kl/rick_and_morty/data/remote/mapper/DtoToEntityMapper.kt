package com.l3on1kl.rick_and_morty.data.remote.mapper

import com.l3on1kl.rick_and_morty.data.local.entity.CharacterEntity
import com.l3on1kl.rick_and_morty.data.remote.model.CharacterDto
import com.l3on1kl.rick_and_morty.domain.model.Gender
import com.l3on1kl.rick_and_morty.domain.model.Status

class DtoToEntityMapper : (CharacterDto) -> CharacterEntity {
    override fun invoke(dto: CharacterDto) = CharacterEntity(
        id = dto.id,
        name = dto.name,
        status = Status.safeValueOf(dto.status),
        species = dto.species,
        type = dto.type,
        gender = Gender.safeValueOf(dto.gender),
        imageUrl = dto.imageUrl,
        originName = dto.origin.name,
        originUrl = dto.origin.url,
        locationName = dto.location.name,
        locationUrl = dto.location.url,
        episodes = dto.episode,
        url = dto.url,
        created = dto.created
    )
}
