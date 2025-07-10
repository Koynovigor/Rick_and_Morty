package com.l3on1kl.rick_and_morty.domain.model

data class Character(
    val id: Int,
    val name: String,
    val status: Status,
    val species: String,
    val gender: Gender,
    val imageUrl: String
)

enum class Status {
    ALIVE,
    DEAD,
    UNKNOWN;

    companion object {
        fun safeValueOf(raw: String?): Status =
            runCatching {
                valueOf(
                    raw?.uppercase() ?: ""
                )
            }.getOrDefault(UNKNOWN)
    }
}

enum class Gender {
    FEMALE,
    MALE,
    GENDERLESS,
    UNKNOWN;

    companion object {
        fun safeValueOf(raw: String?): Gender =
            runCatching {
                valueOf(
                    raw?.uppercase() ?: ""
                )
            }.getOrDefault(UNKNOWN)
    }
}
