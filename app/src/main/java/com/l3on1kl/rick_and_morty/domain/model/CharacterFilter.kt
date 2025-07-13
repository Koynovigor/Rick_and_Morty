package com.l3on1kl.rick_and_morty.domain.model

data class CharacterFilter(
    val name: String? = null,
    val status: Status? = null,
    val species: String? = null,
    val gender: Gender? = null
) {
    val isEmpty: Boolean
        get() = name.isNullOrBlank()
                && status == null
                && species.isNullOrBlank()
                && gender == null
}
