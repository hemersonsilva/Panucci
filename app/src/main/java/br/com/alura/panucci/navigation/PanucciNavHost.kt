package br.com.alura.panucci.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun PanucciNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = homeGraphRoute
    ) {
        homeGraph(
            onNavigateToCheckout = {
                navController.navigateToCheckOut()
            },
            onNavigateToProductDetails = { product ->
                navController.navigateToProductDetails(product.id)
            },
        )
        productDetailsScreen(
            onNavigateToCheckout = {
                navController.navigateToCheckOut()
            },
            onPopBackStack = {
                navController.navigateUp()
            },
        )
        checkOutScreen(
            onPopBackStack = {
                navController.navigateUp()
            },
        )
    }

}