package com.l3on1kl.rick_and_morty.presentation.character_detail.util

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import kotlin.math.max

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun AutoResizeText(
    text: String,
    modifier: Modifier = Modifier,
    maxFontSize: TextUnit = 20.sp,
    minFontSize: TextUnit = 10.sp,
    stepFactor: Float = 0.9f,
    style: TextStyle = TextStyle.Default,
    color: Color
) {
    BoxWithConstraints(modifier) {
        val textMeasurer = rememberTextMeasurer()
        val maxWidthPx = with(LocalDensity.current) { maxWidth.toPx() }

        val chosenSize by remember(
            text,
            maxWidthPx,
            maxFontSize,
            minFontSize,
            stepFactor,
            style
        ) {
            mutableStateOf(
                findOptimalFontSize(
                    text = text,
                    measurer = textMeasurer,
                    containerWidthPx = maxWidthPx,
                    maxFontSize = maxFontSize,
                    minFontSize = minFontSize,
                    stepFactor = stepFactor,
                    baseStyle = style
                )
            )
        }

        Text(
            text = text,
            style = style.copy(fontSize = chosenSize),
            color = color
        )
    }
}

private fun findOptimalFontSize(
    text: String,
    measurer: TextMeasurer,
    containerWidthPx: Float,
    maxFontSize: TextUnit,
    minFontSize: TextUnit,
    stepFactor: Float,
    baseStyle: TextStyle,
): TextUnit {
    var currentSize = maxFontSize
    val words = text.split("\\s+".toRegex()).filter { it.isNotBlank() }

    while (currentSize >= minFontSize) {
        val allFit = words.all { word ->
            wordFits(
                word = word,
                fontSize = currentSize,
                measurer = measurer,
                containerWidthPx = containerWidthPx,
                baseStyle = baseStyle
            )
        }
        if (allFit) return currentSize
        currentSize = max((currentSize.value * stepFactor), minFontSize.value).sp
        if (currentSize == minFontSize) break
    }
    return minFontSize
}

private fun wordFits(
    word: String,
    fontSize: TextUnit,
    measurer: TextMeasurer,
    containerWidthPx: Float,
    baseStyle: TextStyle,
): Boolean {
    val layout = measurer.measure(
        text = AnnotatedString(word),
        style = baseStyle.copy(fontSize = fontSize)
    )
    return layout.size.width <= containerWidthPx
}
