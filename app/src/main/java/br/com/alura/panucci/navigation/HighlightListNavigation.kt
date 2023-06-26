package br.com.alura.panucci.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.screens.HighlightsListScreen

fun NavGraphBuilder.highlightListScreen(navController: NavHostController) {
    composable(AppDestination.Highlight.route) {
        HighlightsListScreen(
            products = sampleProducts,
            onNavigateToDetails = { product ->
                navController.navigate(
                    "${AppDestination.ProductDetails.route}/${product.id}"
                )
            },
            onNavigateToCheckout = {
                navController.navigate(
                    AppDestination.Checkout.route
                )
            }
        )
    }
}