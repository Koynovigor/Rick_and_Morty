package com.l3on1kl.rick_and_morty.presentation.character_detail.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.l3on1kl.rick_and_morty.presentation.theme.StatusAlive
import com.l3on1kl.rick_and_morty.presentation.theme.StatusDead
import com.l3on1kl.rick_and_morty.presentation.theme.StatusUnknown

object DetailUtil {
    fun statusColor(
        status: String
    ): Color = when (status.lowercase()) {
        "alive" -> StatusAlive
        "dead" -> StatusDead
        else -> StatusUnknown
    }

    fun episodeLabel(
        url: String
    ): String {
        val id = url.substringAfterLast("/").toIntOrNull() ?: return "Episode"
        return "E#$id"
    }

    fun lerpDp(
        start: Dp,
        end: Dp,
        fraction: Float
    ): Dp = (start.value + (end.value - start.value) * fraction).dp
}
