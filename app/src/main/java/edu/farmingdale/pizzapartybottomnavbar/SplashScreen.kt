package edu.farmingdale.pizzapartybottomnavbar

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// ToDo 1: The splash screen should have the image displayed in the center of the screen.

@Composable
fun SplashScreen(navController: NavHostController) {
    // Start a bit smaller than 1f so the overshoot “pops”
    val scale = remember { Animatable(0.85f) }
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        // Run both animations in parallel
        launch {
            scale.animateTo(
                targetValue = 1.05f, // overshoot past 1.0f
                animationSpec = tween(
                    durationMillis = 900,
                    easing = { OvershootInterpolator(2f).getInterpolation(it) }
                )
            )
            // settle to 1.0f after the overshoot
            scale.animateTo(
                targetValue = 1.0f,
                animationSpec = tween(durationMillis = 200)
            )
        }
        launch {
            alpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 700)
            )
        }

        // Keep the logo visible briefly, then go to the first screen
        delay(1800)
        navController.navigate(BottomNavigationItems.PizzaScreen.route) {
            // Optional: remove Splash from back stack
            popUpTo(navController.graph.startDestinationId) { inclusive = true }
            launchSingleTop = true
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.fsclogo),
            contentDescription = "FSC Logo",
            modifier = Modifier
                .scale(scale.value)
                .alpha(alpha.value)
        )
    }
}
