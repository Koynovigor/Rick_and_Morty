package com.l3on1kl.rick_and_morty.domain.model

data class Character(
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
    val created: String
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
