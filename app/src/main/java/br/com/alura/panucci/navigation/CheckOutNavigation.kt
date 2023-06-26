package br.com.alura.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.screens.CheckoutScreen

private const val checkOutRoute = "checkout"
fun NavGraphBuilder.checkOutScreen(navController: NavHostController) {
    composable(checkOutRoute) {
        CheckoutScreen(
            products = sampleProducts,
            onPopBackStack = {
                navController.navigateUp()
            })
    }
}
fun NavController.navigateToCheckOut(){
    navigate(checkOutRoute)
}