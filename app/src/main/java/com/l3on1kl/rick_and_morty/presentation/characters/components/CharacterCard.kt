package com.l3on1kl.rick_and_morty.presentation.characters.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.l3on1kl.rick_and_morty.R
import com.l3on1kl.rick_and_morty.presentation.characters.model.CharacterUi
import com.l3on1kl.rick_and_morty.presentation.theme.StatusAlive
import com.l3on1kl.rick_and_morty.presentation.theme.StatusDead
import com.l3on1kl.rick_and_morty.presentation.theme.StatusUnknown

@Composable
fun CharacterCard(
    character: CharacterUi,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val shape = RoundedCornerShape(28.dp)

    val (bannerColor, bannerText) = when (character.status) {
        CharacterUi.Status.ALIVE -> StatusAlive to stringResource(R.string.alive)
        CharacterUi.Status.DEAD -> StatusDead to stringResource(R.string.dead)
        CharacterUi.Status.UNKNOWN -> StatusUnknown to stringResource(R.string.unknown)
    }

    val borderBrush = Brush.verticalGradient(
        listOf(
            bannerColor,
            Color.Transparent
        )
    )

    Card(
        modifier = modifier
            .aspectRatio(0.75f)
            .border(
                2.dp,
                borderBrush,
                shape
            )
            .clickable(
                onClick = onClick
            ),
        shape = shape,
        elevation = CardDefaults.elevatedCardElevation(6.dp),
        colors = CardDefaults.elevatedCardColors(
            MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Box(Modifier.fillMaxSize()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(character.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(
                        y = 28.dp,
                        x = (-28).dp
                    )
                    .rotate(-45f)
                    .background(bannerColor)
                    .width(140.dp)
                    .height(28.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = bannerText.uppercase(),
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Black
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.75f)
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(
                        horizontal = 14.dp,
                        vertical = 12.dp
                    )
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = stringResource(
                        R.string.species_and_gender,
                        character.species,
                        stringResource(character.gender.asTextRes())
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = .9f)
                )
            }
        }
    }
}

private fun CharacterUi.Gender.asTextRes(): Int = when (this) {
    CharacterUi.Gender.FEMALE -> R.string.female
    CharacterUi.Gender.MALE -> R.string.male
    CharacterUi.Gender.GENDERLESS -> R.string.genderless
    CharacterUi.Gender.UNKNOWN -> R.string.unknown_gender
}
