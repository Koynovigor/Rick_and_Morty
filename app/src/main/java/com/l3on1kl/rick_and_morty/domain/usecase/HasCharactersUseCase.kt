package com.l3on1kl.rick_and_morty.domain.usecase

import com.l3on1kl.rick_and_morty.domain.model.CharacterFilter
import com.l3on1kl.rick_and_morty.domain.repository.CharacterRepository
import javax.inject.Inject

class HasCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(filter: CharacterFilter = CharacterFilter()) =
        repository.hasLocalCharacters(filter)
}
