package com.l3on1kl.rick_and_morty.presentation.util

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    val isOnline: Flow<Boolean>
}
