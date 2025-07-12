package com.l3on1kl.rick_and_morty.presentation.characters.model

import com.l3on1kl.rick_and_morty.domain.model.Character as DomainCharacter
import com.l3on1kl.rick_and_morty.domain.model.Gender as DomainGender
import com.l3on1kl.rick_and_morty.domain.model.Status as DomainStatus

data class CharacterUi(
    val id: Int,
    val name: String,
    val status: Status,
    val species: String,
    val gender: Gender,
    val imageUrl: String
) {

    enum class Status {
        ALIVE,
        DEAD,
        UNKNOWN
    }

    enum class Gender {
        FEMALE,
        MALE,
        GENDERLESS,
        UNKNOWN
    }
}

fun DomainCharacter.toUi(): CharacterUi =
    CharacterUi(
        id = id,
        name = name,
        status = status.toUiStatus(),
        species = species,
        gender = gender.toUiGender(),
        imageUrl = imageUrl
    )

private fun DomainStatus.toUiStatus(): CharacterUi.Status = when (this) {
    DomainStatus.ALIVE -> CharacterUi.Status.ALIVE
    DomainStatus.DEAD -> CharacterUi.Status.DEAD
    DomainStatus.UNKNOWN -> CharacterUi.Status.UNKNOWN
}

private fun DomainGender.toUiGender(): CharacterUi.Gender = when (this) {
    DomainGender.FEMALE -> CharacterUi.Gender.FEMALE
    DomainGender.MALE -> CharacterUi.Gender.MALE
    DomainGender.GENDERLESS -> CharacterUi.Gender.GENDERLESS
    DomainGender.UNKNOWN -> CharacterUi.Gender.UNKNOWN
}
