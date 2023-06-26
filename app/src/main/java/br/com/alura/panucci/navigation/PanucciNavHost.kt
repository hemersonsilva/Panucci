package br.com.alura.panucci.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun PanucciNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = highlightListRoute,
        builder = {
            highlightListScreen(navController)
            menuScreen(navController)
            drinksScreen(navController)
            productDetailsScreen(navController)
            checkOutScreen(navController)
        }
    )
}
