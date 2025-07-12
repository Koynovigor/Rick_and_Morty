package com.l3on1kl.rick_and_morty

import com.l3on1kl.rick_and_morty.presentation.util.DateFormatter
import org.junit.Assert.assertEquals
import org.junit.Test

class DateFormatterTest {
    @Test
    fun `parse and format date`() {
        val raw = "2020-07-10T12:30:00.000Z"
        val formatted = DateFormatter.parseAndFormatDate(raw)
        assertEquals("10 Jul 2020, 12:30", formatted)
    }
}
