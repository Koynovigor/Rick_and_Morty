package com.l3on1kl.rick_and_morty.presentation.character_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.l3on1kl.rick_and_morty.presentation.character_detail.util.DetailUtil.statusColor
import com.l3on1kl.rick_and_morty.presentation.theme.RickAndMortyTheme

@Composable
fun StatusBadge(
    status: String
) {
    val color = statusColor(status)
    AssistChip(
        onClick = {},
        label = {
            Text(
                text = status,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = Bold
                )
            )
        },
        leadingIcon = {
            Box(
                Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = color.copy(alpha = 0.5f)
        )
    )
}


@Preview(showBackground = true, name = "All StatusBadges")
@Composable
fun AllStatusBadgesPreview() {
    RickAndMortyTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StatusBadge(status = "Alive")
            StatusBadge(status = "Dead")
            StatusBadge(status = "unknown")
        }
    }
}