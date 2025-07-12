package com.l3on1kl.rick_and_morty.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.l3on1kl.rick_and_morty.presentation.character_detail.CharacterDetailRoute
import com.l3on1kl.rick_and_morty.presentation.characters.CharacterListRoute

sealed class Screen(val route: String) {
    data object Characters : Screen("characters")
    data object CharacterDetails : Screen("character/{id}") {
        fun create(id: Int) = "character/$id"
    }
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
            CharacterListRoute(
                onItemClick = {
                    navController.navigate(
                        Screen.CharacterDetails.create(it.id)
                    )
                }
            )
        }

        composable(
            route = Screen.CharacterDetails.route,
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            CharacterDetailRoute()
        }
    }
}
