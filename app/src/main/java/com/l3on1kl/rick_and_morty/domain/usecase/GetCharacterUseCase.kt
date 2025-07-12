package com.l3on1kl.rick_and_morty.domain.usecase

import com.l3on1kl.rick_and_morty.domain.model.Character
import com.l3on1kl.rick_and_morty.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository,
) {
    suspend operator fun invoke(
        id: Int
    ): Character? = repository.getCharacter(id)
}
