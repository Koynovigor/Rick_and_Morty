package com.l3on1kl.rick_and_morty.domain.usecase

import com.l3on1kl.rick_and_morty.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke() = repository.getCharacters()
}
