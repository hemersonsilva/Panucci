package br.com.alura.panucci

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PointOfSale
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.alura.panucci.navigation.PanucciNavHost
import br.com.alura.panucci.navigation.drinksRoute
import br.com.alura.panucci.navigation.highlightsListRoute
import br.com.alura.panucci.navigation.menuRoute
import br.com.alura.panucci.navigation.navigateSingleTopWithPopUpTo
import br.com.alura.panucci.navigation.navigateToCheckOut
import br.com.alura.panucci.ui.components.BottomAppBarItem
import br.com.alura.panucci.ui.components.PanucciBottomAppBar
import br.com.alura.panucci.ui.components.bottomAppBarItems
import br.com.alura.panucci.ui.screens.*
import br.com.alura.panucci.ui.theme.PanucciTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()
            val backStackEntryAsState by navController.currentBackStackEntryAsState()
            val orderDoneMessage = backStackEntryAsState
                ?.savedStateHandle
                ?.getStateFlow<String?>("order_done", null)?.collectAsState()
            val currentDestination = backStackEntryAsState?.destination

            val snackbarHostState = remember {
                SnackbarHostState()
            }

            val scope = rememberCoroutineScope()

            orderDoneMessage?.value?.let { message ->
            scope.launch {
                    snackbarHostState.showSnackbar(message = message)
                }
            }

            PanucciTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val currentRoute = currentDestination?.route

                    val selectedItem by remember(currentDestination) {
                        val item = when (currentRoute) {
                            highlightsListRoute -> BottomAppBarItem.HighlightsList
                            menuRoute -> BottomAppBarItem.Menu
                            drinksRoute -> BottomAppBarItem.Drinks
                            else -> BottomAppBarItem.HighlightsList
                        }
                        mutableStateOf(item)
                    }

                    val containsInBottomAppBarItems = when(currentRoute){
                        highlightsListRoute, menuRoute, drinksRoute -> true
                        else -> false
                    }

                    val isShowFab = when (currentRoute) {
                        menuRoute, drinksRoute -> true
                        else -> false
                    }

                    PanucciApp(
                        snackbarHostState = snackbarHostState,
                        bottomAppBarItemSelected = selectedItem,
                        onBottomAppBarItemSelectedChange = { item ->
                            navController.navigateSingleTopWithPopUpTo(item)
                        },
                        onFabClick = {
                            navController.navigateToCheckOut()
                        },
                        isShowTopBar = containsInBottomAppBarItems,
                        isShowBottomBar = containsInBottomAppBarItems,
                        isShowFab = isShowFab
                    ) {
                        PanucciNavHost(navController = navController)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PanucciApp(
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    bottomAppBarItemSelected: BottomAppBarItem = bottomAppBarItems.first(),
    onBottomAppBarItemSelectedChange: (BottomAppBarItem) -> Unit = {},
    onFabClick: () -> Unit = {},
    isShowTopBar: Boolean = false,
    isShowBottomBar: Boolean = false,
    isShowFab: Boolean = false,
    content: @Composable () -> Unit
) {
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(Modifier.padding(horizontal = 24.dp)) {
                    Text(text = data.visuals.message)
                }
            }
        },
        topBar = {
            if (isShowTopBar) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = "Ristorante Panucci")
                    },
                )
            }
        },
        bottomBar = {
            if (isShowBottomBar) {
                PanucciBottomAppBar(
                    item = bottomAppBarItemSelected,
                    items = bottomAppBarItems,
                    onItemChange = onBottomAppBarItemSelectedChange,
                )
            }
        },

        floatingActionButton = {
            if (isShowFab) {
                FloatingActionButton(
                    onClick = onFabClick
                ) {
                    Icon(
                        Icons.Filled.PointOfSale,
                        contentDescription = null
                    )
                }
            }
        }
    ) {
        Box(
            modifier = Modifier.padding(it)
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun PanucciAppPreview() {
    PanucciTheme {
        Surface {
            PanucciApp(content = {})
        }
    }
}