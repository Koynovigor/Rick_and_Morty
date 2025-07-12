package com.l3on1kl.rick_and_morty.data.remote.api

import com.l3on1kl.rick_and_morty.data.remote.model.CharacterResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int? = null,
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("species") species: String? = null,
        @Query("gender") gender: String? = null,
    ): CharacterResponseDto
}
