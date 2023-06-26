package br.com.alura.panucci.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.screens.ProductDetailsScreen

private const val productDetailsRoute = "productDetails"
private const val productIdArgument = "productId"

fun NavGraphBuilder.productDetailsScreen(navController: NavHostController) {
    composable(
        "$productDetailsRoute/{$productIdArgument}"
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getString("productId")
        sampleProducts.find {
            it.id == id
        }?.let { product ->
            ProductDetailsScreen(
                product = product,
                onNavigateToCheckout = {
                    navController.navigateToCheckOut()
                }
            )
        } ?: LaunchedEffect(key1 = Unit, block = {
            navController.navigateUp()
        })
    }
}
fun NavController.navigateToProductDetails(id: String){
    navigate("$productDetailsRoute/$id")
}