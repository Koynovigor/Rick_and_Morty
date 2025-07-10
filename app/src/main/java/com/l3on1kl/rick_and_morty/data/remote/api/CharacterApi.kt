package com.l3on1kl.rick_and_morty.data.remote.api

import com.l3on1kl.rick_and_morty.data.remote.model.CharacterResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int? = null
    ): CharacterResponseDto
}
