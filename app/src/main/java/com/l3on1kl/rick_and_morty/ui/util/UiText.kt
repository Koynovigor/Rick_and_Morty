package com.l3on1kl.rick_and_morty.ui.util

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    data class Dynamic(
        val value: String
    ) : UiText()

    data class Resource(
        val resId: Int
    ) : UiText()

    @Composable
    fun asString(): String = when (this) {
        is Dynamic -> value
        is Resource -> stringResource(resId)
    }

    fun asString(context: Context): String = when (this) {
        is Dynamic -> value
        is Resource -> context.getString(resId)
    }
}
