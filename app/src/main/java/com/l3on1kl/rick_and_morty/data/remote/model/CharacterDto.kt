package com.l3on1kl.rick_and_morty.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationDto,
    val location: LocationDto,
    @SerialName("image") val imageUrl: String,
    val episode: List<String>,
    val url: String,
    val created: String,
)

@Serializable
data class LocationDto(
    val name: String,
    val url: String,
)
