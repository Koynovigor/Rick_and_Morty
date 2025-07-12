package com.l3on1kl.rick_and_morty.domain.repository

import androidx.paging.PagingData
import com.l3on1kl.rick_and_morty.domain.model.Character
import com.l3on1kl.rick_and_morty.domain.model.CharacterFilter
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(
        filter: CharacterFilter = CharacterFilter(),
        online: Boolean = true
    ): Flow<PagingData<Character>>

    fun hasLocalCharacters(
        filter: CharacterFilter = CharacterFilter()
    ): Flow<Boolean>
}
