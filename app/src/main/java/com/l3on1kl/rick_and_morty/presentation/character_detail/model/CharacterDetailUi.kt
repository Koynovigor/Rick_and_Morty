package com.l3on1kl.rick_and_morty.presentation.character_detail.model

import com.l3on1kl.rick_and_morty.domain.model.Character
import com.l3on1kl.rick_and_morty.domain.model.Gender as DomainGender
import com.l3on1kl.rick_and_morty.domain.model.Status as DomainStatus

data class CharacterDetailUi(
    val id: Int,
    val name: String,
    val status: Status,
    val species: String,
    val type: String,
    val gender: Gender,
    val imageUrl: String,
    val originName: String,
    val originUrl: String,
    val locationName: String,
    val locationUrl: String,
    val episodes: List<String>,
    val url: String,
    val created: String,
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

fun Character.toDetailUi(): CharacterDetailUi = CharacterDetailUi(
    id = id,
    name = name,
    status = status.toUiStatus(),
    species = species,
    type = type,
    gender = gender.toUiGender(),
    imageUrl = imageUrl,
    originName = originName,
    originUrl = originUrl,
    locationName = locationName,
    locationUrl = locationUrl,
    episodes = episodes,
    url = url,
    created = created,
)

private fun DomainStatus.toUiStatus(): CharacterDetailUi.Status = when (this) {
    DomainStatus.ALIVE -> CharacterDetailUi.Status.ALIVE
    DomainStatus.DEAD -> CharacterDetailUi.Status.DEAD
    DomainStatus.UNKNOWN -> CharacterDetailUi.Status.UNKNOWN
}

private fun DomainGender.toUiGender(): CharacterDetailUi.Gender = when (this) {
    DomainGender.FEMALE -> CharacterDetailUi.Gender.FEMALE
    DomainGender.MALE -> CharacterDetailUi.Gender.MALE
    DomainGender.GENDERLESS -> CharacterDetailUi.Gender.GENDERLESS
    DomainGender.UNKNOWN -> CharacterDetailUi.Gender.UNKNOWN
}
