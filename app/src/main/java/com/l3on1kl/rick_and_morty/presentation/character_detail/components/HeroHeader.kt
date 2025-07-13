package com.l3on1kl.rick_and_morty.presentation.character_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.l3on1kl.rick_and_morty.R
import com.l3on1kl.rick_and_morty.presentation.character_detail.util.AutoResizeText
import com.l3on1kl.rick_and_morty.presentation.character_detail.util.DetailUtil.statusColor
import com.l3on1kl.rick_and_morty.presentation.theme.RickAndMortyTheme

@Composable
fun HeroHeader(
    imageUrl: String,
    name: String,
    statusRes: Int,
    avatarSize: Dp,
    heroHeight: Dp,
) {
    val context = LocalContext.current
    val imageRequest =
        remember(imageUrl) {
            ImageRequest
                .Builder(context)
                .data(imageUrl)
                .crossfade(100)
                .build()
        }

    Box(
        modifier = Modifier.height(heroHeight)
    ) {
        AsyncImage(
            model = imageRequest,
            contentDescription = name,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        0f to Color.Black.copy(alpha = 0.55f),
                        0.4f to Color.Transparent,
                        1f to Color.Black.copy(alpha = 0.85f)
                    )
                )
        )

        Column(
            Modifier
                .align(Alignment.BottomStart)
                .padding(24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = imageRequest,
                    contentDescription = null,
                    modifier = Modifier
                        .size(avatarSize)
                        .clip(CircleShape)
                        .border(
                            width = 2.dp,
                            color = statusColor(stringResource(statusRes)),
                            shape = CircleShape
                        ),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.width(16.dp))
                Column {
                    AutoResizeText(
                        name,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White
                    )
                    StatusBadge(stringResource(statusRes))
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "HeroHeader Preview", widthDp = 360, heightDp = 300)
@Composable
fun HeroHeaderPreview() {
    RickAndMortyTheme {
        val sampleImageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        val sampleName = "Rick Sanchez"
        val sampleStatusRes = R.string.alive

        HeroHeader(
            imageUrl = sampleImageUrl,
            name = sampleName,
            statusRes = sampleStatusRes,
            avatarSize = 60.dp,
            heroHeight = 300.dp
        )
    }
}
