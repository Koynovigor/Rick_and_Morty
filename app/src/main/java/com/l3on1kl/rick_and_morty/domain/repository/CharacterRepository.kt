package com.l3on1kl.rick_and_morty.domain.repository

import androidx.paging.PagingData
import com.l3on1kl.rick_and_morty.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(): Flow<PagingData<Character>>
}
