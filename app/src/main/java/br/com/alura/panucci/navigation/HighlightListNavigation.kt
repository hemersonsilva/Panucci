package br.com.alura.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.screens.HighlightsListScreen

internal const val highlightListRoute = "highlight"

fun NavGraphBuilder.highlightListScreen(navController: NavHostController) {

    composable(highlightListRoute) {
        HighlightsListScreen(
            products = sampleProducts,
            onNavigateToDetails = { product ->
                navController.navigateToProductDetails(product.id)
            },
            onNavigateToCheckout = {
                navController.navigateToCheckOut()
            }
        )
    }
}
fun NavController.navigateToHighLightsList(){
    navigate(highlightListRoute)
}