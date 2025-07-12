package com.l3on1kl.rick_and_morty

import com.l3on1kl.rick_and_morty.domain.model.CharacterFilter
import com.l3on1kl.rick_and_morty.domain.model.Gender
import com.l3on1kl.rick_and_morty.domain.model.Status
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CharacterFilterTest {
    @Test
    fun `empty filter is empty`() {
        val filter = CharacterFilter()
        assertTrue(filter.isEmpty)
    }

    @Test
    fun `filter with name is not empty`() {
        val filter = CharacterFilter(name = "Rick")
        assertFalse(filter.isEmpty)
    }

    @Test
    fun `filter with status is not empty`() {
        val filter = CharacterFilter(status = Status.ALIVE)
        assertFalse(filter.isEmpty)
    }

    @Test
    fun `filter with species is not empty`() {
        val filter = CharacterFilter(species = "Human")
        assertFalse(filter.isEmpty)
    }

    @Test
    fun `filter with gender is not empty`() {
        val filter = CharacterFilter(gender = Gender.MALE)
        assertFalse(filter.isEmpty)
    }
}
