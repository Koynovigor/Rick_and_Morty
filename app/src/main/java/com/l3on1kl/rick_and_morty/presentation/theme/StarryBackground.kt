package com.l3on1kl.rick_and_morty.presentation.theme

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlin.random.Random

private data class Star(
    val offset: Offset,
    val radiusPx: Float,
    val maxAlpha: Float,
    val animSpec: InfiniteRepeatableSpec<Float>
)

@Composable
fun StarryBackground(
    modifier: Modifier = Modifier,
    starsCount: Int = 200
) {
    val isDark = isSystemInDarkTheme()
    val skyColor =
        if (isDark) Color.Black else Color(0xFFF0FFFF)

    val starColor =
        if (isDark) Color.White else Color.Black

    val density = LocalDensity.current
    val minRadius = with(density) {
        1.dp.toPx()
    }
    val maxRadius = with(density) {
        2.5.dp.toPx()
    }

    val stars = remember(starsCount) {
        List(starsCount) {
            val radius = Random.nextFloat() * (maxRadius - minRadius) + minRadius
            val alpha = Random.nextFloat() * .3f + .7f
            val duration = Random.nextInt(2_000, 6_000)
            val delay = Random.nextInt(0, 4_000)

            Star(
                offset = Offset(
                    Random.nextFloat(),
                    Random.nextFloat()
                ),
                radiusPx = radius,
                maxAlpha = alpha,
                animSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = duration,
                        easing = LinearEasing,
                        delayMillis = delay
                    ),
                    repeatMode = RepeatMode.Reverse
                )
            )
        }
    }

    val twinkles = rememberInfiniteTransition(
        label = "twinkles"
    )

    val alphas = stars.mapIndexed { index, star ->
        twinkles.animateFloat(
            initialValue = 0f,
            targetValue = star.maxAlpha,
            animationSpec = star.animSpec,
            label = "alpha$index"
        )
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        drawRect(skyColor)

        stars.forEachIndexed { i, star ->
            val pos = Offset(
                x = star.offset.x * size.width,
                y = star.offset.y * size.height
            )
            drawCircle(
                color = starColor.copy(alpha = alphas[i].value),
                radius = star.radiusPx,
                center = pos
            )
        }
    }
}
