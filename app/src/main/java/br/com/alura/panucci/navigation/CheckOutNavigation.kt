package br.com.alura.panucci.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.screens.CheckoutScreen

fun NavGraphBuilder.checkOutScreen(navController: NavHostController) {
    composable(AppDestination.Checkout.route) {
        CheckoutScreen(
            products = sampleProducts,
            onPopBackStack = {
                navController.navigateUp()
            })
    }
}