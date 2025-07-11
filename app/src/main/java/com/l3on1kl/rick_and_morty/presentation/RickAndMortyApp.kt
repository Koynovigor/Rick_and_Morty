package com.l3on1kl.rick_and_morty.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.l3on1kl.rick_and_morty.presentation.navigation.AppNavGraph
import com.l3on1kl.rick_and_morty.presentation.theme.RickAndMortyTheme
import com.l3on1kl.rick_and_morty.presentation.theme.StarryBackground

@Composable
fun RickAndMortyApp() {
    RickAndMortyTheme {
        Box(Modifier.fillMaxSize()) {
            StarryBackground(
                modifier = Modifier.fillMaxSize()
            )

            val navController = rememberNavController()
            AppNavGraph(navController)
        }
    }
}
