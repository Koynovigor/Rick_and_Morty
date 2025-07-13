package com.l3on1kl.rick_and_morty.presentation.util

import android.content.Context

sealed class UiText {
    data class Dynamic(
        val value: String
    ) : UiText()

    data class Resource(
        val resId: Int
    ) : UiText()

    fun asString(context: Context): String = when (this) {
        is Dynamic -> value
        is Resource -> context.getString(resId)
    }
}
