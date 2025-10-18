package edu.farmingdale.pizzapartybottomnavbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class) // TopAppBar + ModalNavigationDrawer use M3 APIs
@Composable
fun AppWithDrawer() {
    // Single NavController for the whole app
    val nav = rememberNavController()

    // Controls open/close state of the side drawer
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    // Needed to call suspend functions (open/close the drawer) from callbacks
    val scope = rememberCoroutineScope()

    // Bottom bar visibility is still driven by NavigationGraph via the callback
    var bottomBarVisible by remember { mutableStateOf(true) }

    // Observe current back stack entry so we can:
    //  (1) set the dynamic AppBar title
    //  (2) highlight the selected row in the drawer
    val currentRoute = nav.currentBackStackEntryAsState().value?.destination?.route
        ?: BottomNavigationItems.Welcome.route

    // Human-readable titles for the AppBar
    val titleForRoute = when (currentRoute) {
        BottomNavigationItems.PizzaScreen.route -> "Pizza Order"
        BottomNavigationItems.GpaAppScreen.route -> "GPA App"
        BottomNavigationItems.Screen3.route -> "Screen3"
        else -> "Welcome"
    }

    // ModalNavigationDrawer = adds the swipe-in drawer + scrim
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // ModalDrawerSheet gives the drawer its solid, opaque background (white)
            ModalDrawerSheet(
                drawerContainerColor = Color.White
            ) {
                // Our custom list of drawer items
                DrawerContent(
                    selectedRoute = currentRoute,
                    onSelect = { route ->
                        // Navigate to the selected destination
                        nav.navigate(route) {
                            // Keep a shallow back stack; avoid piling duplicates
                            popUpTo(BottomNavigationItems.Welcome.route) { inclusive = false }
                            launchSingleTop = true
                        }
                        // Close the drawer after selection
                        scope.launch { drawerState.close() }
                    }
                )
            }
        },
        // Semi-transparent overlay over main content while drawer is open
        scrimColor = DrawerDefaults.scrimColor
    ) {
        // Scaffold gives us AppBar + content slot + (optional) bottom bar
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(titleForRoute) },
                    navigationIcon = {
                        // “Hamburger” button opens the drawer
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Open drawer")
                        }
                    },
                    // Colors matched to your mock (brand purple app bar + white content)
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF6C4CCF),
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White
                    )
                )
            },
            // Page background that peeks around the drawer (subtle lilac)
            containerColor = Color(0xFFF4EFFA),
            bottomBar = {
                // Keep using your existing BottomBar when the graph asks to show it
                if (bottomBarVisible) {
                    BottomBar(navController = nav, state = bottomBarVisible, modifier = Modifier)
                }
            }
        ) { innerPadding ->
            // Navigation host area — we pass a callback so screens can show/hide the bottom bar
            Box(Modifier.padding(innerPadding)) {
                NavigationGraph(
                    navController = nav,
                    onBottomBarVisibilityChanged = { bottomBarVisible = it }
                )
            }
        }
    }
}
