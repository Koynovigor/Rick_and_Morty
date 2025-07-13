package com.l3on1kl.rick_and_morty.data.local.entity

import com.l3on1kl.rick_and_morty.domain.model.Character

class EntityToDomainMapper : (CharacterEntity) -> Character {
    override fun invoke(entity: CharacterEntity) = Character(
        id = entity.id,
        name = entity.name,
        status = entity.status,
        species = entity.species,
        type = entity.type,
        gender = entity.gender,
        imageUrl = entity.imageUrl,
        originName = entity.originName,
        originUrl = entity.originUrl,
        locationName = entity.locationName,
        locationUrl = entity.locationUrl,
        episodes = entity.episodes,
        url = entity.url,
        created = entity.created
    )
}
