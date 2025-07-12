package com.l3on1kl.rick_and_morty.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.l3on1kl.rick_and_morty.presentation.characters.CharacterListRoute

sealed class Screen(val route: String) {
    data object Characters : Screen("characters")
}

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Characters.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Characters.route) {
            CharacterListRoute()
        }
    }
}
