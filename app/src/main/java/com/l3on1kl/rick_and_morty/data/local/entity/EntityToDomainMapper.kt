package com.l3on1kl.rick_and_morty.data.local.entity

import com.l3on1kl.rick_and_morty.domain.model.Character

class EntityToDomainMapper : (CharacterEntity) -> Character {
    override fun invoke(entity: CharacterEntity) = Character(
        id = entity.id,
        name = entity.name,
        status = entity.status,
        species = entity.species,
        gender = entity.gender,
        imageUrl = entity.imageUrl
    )
}
