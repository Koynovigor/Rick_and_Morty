package com.l3on1kl.rick_and_morty

import com.l3on1kl.rick_and_morty.domain.model.Gender
import com.l3on1kl.rick_and_morty.domain.model.Status
import org.junit.Assert.assertEquals
import org.junit.Test

class EnumSafeValueOfTest {
    @Test
    fun `safeValueOf returns correct status`() {
        assertEquals(Status.ALIVE, Status.safeValueOf("alive"))
        assertEquals(Status.DEAD, Status.safeValueOf("dead"))
        assertEquals(Status.UNKNOWN, Status.safeValueOf("other"))
        assertEquals(Status.UNKNOWN, Status.safeValueOf(null))
    }

    @Test
    fun `safeValueOf returns correct gender`() {
        assertEquals(Gender.MALE, Gender.safeValueOf("male"))
        assertEquals(Gender.FEMALE, Gender.safeValueOf("female"))
        assertEquals(Gender.UNKNOWN, Gender.safeValueOf("some"))
        assertEquals(Gender.UNKNOWN, Gender.safeValueOf(null))
    }
}
