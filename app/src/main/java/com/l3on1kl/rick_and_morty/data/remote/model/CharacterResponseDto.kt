package com.l3on1kl.rick_and_morty.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponseDto(
    @SerialName("info") val pageInfo: PageInfoDto,
    @SerialName("results") val results: List<CharacterDto>
)

@Serializable
data class PageInfoDto(
    val count: Int,
    val pages: Int,
    val next: String? = null,
    val prev: String? = null
)
