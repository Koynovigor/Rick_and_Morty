package com.l3on1kl.rick_and_morty.presentation.util

import com.l3on1kl.rick_and_morty.R
import com.l3on1kl.rick_and_morty.domain.model.Gender
import com.l3on1kl.rick_and_morty.domain.model.Status

object TextResMapper {
    fun Status.asTextRes(): Int = when (this) {
        Status.ALIVE -> R.string.alive
        Status.DEAD -> R.string.dead
        Status.UNKNOWN -> R.string.unknown
    }

    fun Gender.asTextRes(): Int = when (this) {
        Gender.FEMALE -> R.string.female
        Gender.MALE -> R.string.male
        Gender.GENDERLESS -> R.string.genderless
        Gender.UNKNOWN -> R.string.unknown_gender
    }
}
