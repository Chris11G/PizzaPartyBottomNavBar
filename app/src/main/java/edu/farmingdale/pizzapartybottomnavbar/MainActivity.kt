package edu.farmingdale.pizzapartybottomnavbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import edu.farmingdale.pizzapartybottomnavbar.ui.theme.PizzaPartyBottomNavBarTheme

/**
 * Entry point of the app.
 * We keep Activity super thin and hand off to Compose immediately.
 * AppWithDrawer() hosts:
 *  - the TopAppBar,
 *  - the Modal drawer (left side menu),
 *  - your NavigationGraph (screens),
 *  - and your BottomBar (when enabled by the graph).
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Your Material3 theme
            PizzaPartyBottomNavBarTheme {
                // Entire app UI scaffold (drawer + app bar + content + optional bottom bar)
                AppWithDrawer()
            }
        }
    }
}
