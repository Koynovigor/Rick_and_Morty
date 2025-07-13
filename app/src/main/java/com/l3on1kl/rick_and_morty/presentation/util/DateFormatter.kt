package com.l3on1kl.rick_and_morty.presentation.util

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateFormatter {
    fun parseAndFormatDate(raw: String): String {
        val inputFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME

        val outputFormatter = DateTimeFormatter.ofPattern(
            "dd MMM yyyy, HH:mm",
            Locale.ENGLISH
        )

        val dateTime = ZonedDateTime.parse(
            raw,
            inputFormatter
        )

        return dateTime.format(outputFormatter)
    }
}
