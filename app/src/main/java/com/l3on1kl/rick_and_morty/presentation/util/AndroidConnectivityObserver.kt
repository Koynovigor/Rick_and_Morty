package com.l3on1kl.rick_and_morty.presentation.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidConnectivityObserver @Inject constructor(
    @param:ApplicationContext private val context: Context
) : ConnectivityObserver {

    override val isOnline: Flow<Boolean> = callbackFlow {
        val connectivityManager = context.getSystemService(
            ConnectivityManager::class.java
        )

        fun push() {
            val connected = connectivityManager.activeNetwork?.let { network ->
                connectivityManager.getNetworkCapabilities(network)
                    ?.hasCapability(
                        NetworkCapabilities.NET_CAPABILITY_INTERNET
                    )
            } == true
            trySend(connected)
        }

        push()

        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(
                network: Network
            ) = push()

            override fun onLost(
                network: Network
            ) = push()

            override fun onCapabilitiesChanged(
                network: Network,
                capabilities: NetworkCapabilities
            ) = push()
        }

        connectivityManager.registerNetworkCallback(
            NetworkRequest.Builder()
                .addCapability(
                    NetworkCapabilities.NET_CAPABILITY_INTERNET
                )
                .build(),
            callback
        )

        awaitClose {
            connectivityManager.unregisterNetworkCallback(
                callback
            )
        }
    }.distinctUntilChanged()
}
