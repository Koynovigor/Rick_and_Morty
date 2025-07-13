package com.l3on1kl.rick_and_morty

import com.l3on1kl.rick_and_morty.data.local.util.EnumConverters
import com.l3on1kl.rick_and_morty.domain.model.Gender
import com.l3on1kl.rick_and_morty.domain.model.Status
import org.junit.Assert.assertEquals
import org.junit.Test

class EnumConvertersTest {
    @Test
    fun `status conversion`() {
        val status = EnumConverters.toStatus("alive")
        assertEquals(Status.ALIVE, status)
        assertEquals("ALIVE", EnumConverters.fromStatus(status))
    }

    @Test
    fun `gender conversion`() {
        val gender = EnumConverters.toGender("male")
        assertEquals(Gender.MALE, gender)
        assertEquals("MALE", EnumConverters.fromGender(gender))
    }

    @Test
    fun `string list conversion`() {
        val list = listOf("1", "2", "3")
        val stored = EnumConverters.fromStringList(list)
        assertEquals(list, EnumConverters.toStringList(stored))
    }
}
