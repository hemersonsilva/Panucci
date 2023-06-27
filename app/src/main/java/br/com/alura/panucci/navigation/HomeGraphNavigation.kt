package br.com.alura.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import androidx.navigation.navigation
import br.com.alura.panucci.ui.components.BottomAppBarItem

internal const val homeGraphRoute = "home"

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation(startDestination = highlightListRoute, route = homeGraphRoute) {
        highlightListScreen(navController)
        menuScreen(navController)
        drinksScreen(navController)
    }
}

fun NavController.navigateToHomeGraph(){
    navigate(homeGraphRoute)
}

fun NavController.navigateSingleTopWithPopUpTo(
    item: BottomAppBarItem
) {
    val (route, navigate) = when (item) {
        BottomAppBarItem.Drinks -> Pair(
            drinksRoute,
            ::navigateToDrinks
        )

        BottomAppBarItem.HighlightsList -> Pair(
            highlightListRoute,
            ::navigateToHighLightsList
        )

        BottomAppBarItem.Menu -> Pair(
            menuRoute,
            ::navigateToMenu
        )
    }

    val navOptions = navOptions {
        launchSingleTop = true
        popUpTo(route = route)
    }

    navigate(navOptions)
}