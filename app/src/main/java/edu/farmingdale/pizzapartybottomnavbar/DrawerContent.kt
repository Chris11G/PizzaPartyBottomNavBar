package edu.farmingdale.pizzapartybottomnavbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * DrawerContent: the vertical list you see inside the white drawer sheet.
 * - selectedRoute highlights the currently active destination.
 * - onSelect(route) is invoked when a row is tapped.
 */
@Composable
fun DrawerContent(
    selectedRoute: String,
    onSelect: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(280.dp)   // typical drawer width on phones
            .padding(16.dp)
    ) {
        Spacer(Modifier.height(8.dp))

        // Row 1: Pizza Order
        DrawerRow(
            icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = "Pizza") },
            label = "Pizza Order",
            selected = selectedRoute == BottomNavigationItems.PizzaScreen.route,
            onClick = { onSelect(BottomNavigationItems.PizzaScreen.route) }
        )

        Spacer(Modifier.height(12.dp))

        // Row 2: GPA App
        DrawerRow(
            icon = { Icon(Icons.Filled.Info, contentDescription = "GPA") },
            label = "GPA App",
            selected = selectedRoute == BottomNavigationItems.GpaAppScreen.route,
            onClick = { onSelect(BottomNavigationItems.GpaAppScreen.route) }
        )

        Spacer(Modifier.height(12.dp))

        // Row 3: Screen3
        DrawerRow(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Screen3") },
            label = "Screen3",
            selected = selectedRoute == BottomNavigationItems.Screen3.route,
            onClick = { onSelect(BottomNavigationItems.Screen3.route) }
        )
    }
}

/**
 * DrawerRow: a single selectable pill-shaped row.
 * - Uses a light purple background when selected to match your mock.
 */
@Composable
private fun DrawerRow(
    icon: @Composable () -> Unit,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val bg = if (selected) Color(0xFFE8DEF8) else Color.Transparent // “pill” highlight
    val fg = Color(0xFF1C1B1F) // on-surface text color

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(28.dp))  // rounded pill corners
            .background(bg)                    // only visible when selected
            .clickable(onClick = onClick)      // whole row is tappable
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        // Icon area (kept simple; you can tint here if needed)
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
        ) { icon() }

        Spacer(Modifier.width(16.dp))

        // Label text (M3 bodyLarge gives a good readable size)
        Text(text = label, color = fg, style = MaterialTheme.typography.bodyLarge)
    }
}
