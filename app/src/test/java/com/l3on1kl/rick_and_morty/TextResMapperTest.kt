package com.l3on1kl.rick_and_morty

import com.l3on1kl.rick_and_morty.domain.model.Gender
import com.l3on1kl.rick_and_morty.domain.model.Status
import com.l3on1kl.rick_and_morty.presentation.util.TextResMapper.asTextRes
import org.junit.Assert.assertEquals
import org.junit.Test

class TextResMapperTest {
    @Test
    fun `status to text res`() {
        assertEquals(R.string.alive, Status.ALIVE.asTextRes())
        assertEquals(R.string.dead, Status.DEAD.asTextRes())
        assertEquals(R.string.unknown, Status.UNKNOWN.asTextRes())
    }

    @Test
    fun `gender to text res`() {
        assertEquals(R.string.female, Gender.FEMALE.asTextRes())
        assertEquals(R.string.male, Gender.MALE.asTextRes())
        assertEquals(R.string.genderless, Gender.GENDERLESS.asTextRes())
        assertEquals(R.string.unknown_gender, Gender.UNKNOWN.asTextRes())
    }
}
