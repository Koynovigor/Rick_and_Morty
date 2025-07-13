package com.l3on1kl.rick_and_morty.presentation.util

import com.l3on1kl.rick_and_morty.R
import java.io.IOException
import javax.inject.Inject

class ErrorMapper @Inject constructor() {
    fun map(throwable: Throwable): UiText = when (throwable) {
        is java.net.UnknownHostException ->
            UiText.Resource(R.string.no_connection)

        is java.net.SocketTimeoutException ->
            UiText.Resource(R.string.timeout)

        is retrofit2.HttpException ->
            UiText.Resource(R.string.server_error)

        is IOException ->
            UiText.Resource(R.string.network_error)

        else -> UiText.Resource(R.string.unknown_error)
    }
}
