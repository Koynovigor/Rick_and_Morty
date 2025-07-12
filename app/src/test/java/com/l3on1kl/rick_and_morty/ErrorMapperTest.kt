package com.l3on1kl.rick_and_morty

import com.l3on1kl.rick_and_morty.presentation.util.ErrorMapper
import com.l3on1kl.rick_and_morty.presentation.util.UiText
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

class ErrorMapperTest {
    private val mapper = ErrorMapper()

    @Test
    fun `unknown host maps to no connection`() {
        val res = mapper.map(java.net.UnknownHostException())
        assertEquals(R.string.no_connection, (res as UiText.Resource).resId)
    }

    @Test
    fun `socket timeout maps to timeout`() {
        val res = mapper.map(java.net.SocketTimeoutException())
        assertEquals(R.string.timeout, (res as UiText.Resource).resId)
    }

    @Test
    fun `io exception maps to network error`() {
        val res = mapper.map(IOException())
        assertEquals(R.string.network_error, (res as UiText.Resource).resId)
    }

    @Test
    fun `http exception maps to server error`() {
        val body = "".toResponseBody(null)
        val response = retrofit2.Response.error<Any>(500, body)
        val res = mapper.map(retrofit2.HttpException(response))
        assertEquals(R.string.server_error, (res as UiText.Resource).resId)
    }

    @Test
    fun `other exception maps to unknown error`() {
        val res = mapper.map(RuntimeException())
        assertEquals(R.string.unknown_error, (res as UiText.Resource).resId)
    }
}
