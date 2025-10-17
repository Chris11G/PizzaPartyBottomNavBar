package edu.farmingdale.pizzapartybottomnavbar

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Screen3() {
    var sliderValue by remember { mutableStateOf(0.5f) }
    var chkd by remember { mutableStateOf(true) }

    val context = LocalContext.current

    // 🎨 Dynamically adjust gradient colors based on slider value
    val topColor = Color(
        red = (178 - 78 * sliderValue).toInt(),
        green = (255 - 55 * sliderValue).toInt(),
        blue = (89 - 39 * sliderValue).toInt()
    )

    val bottomColor = Color(
        red = (46 - 20 * sliderValue).toInt(),
        green = (125 - 25 * sliderValue).toInt(),
        blue = (50 - 10 * sliderValue).toInt()
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(topColor, bottomColor)
                )
            )
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 🌈 Slider controls gradient intensity
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            modifier = Modifier.fillMaxWidth(),
            enabled = chkd
        )

        // Display slider value
        Text(
            fontSize = 20.sp,
            text = "Gradient Intensity: ${String.format("%.2f", sliderValue)}"
        )

        Button(
            onClick = {
                val newInt = Intent(Intent.ACTION_VIEW)
                newInt.data = Uri.parse("tel:6314202000")
                context.startActivity(newInt)
            }
        ) {
            Text(fontSize = 20.sp, text = "Call me")
        }

        Checkbox(
            checked = chkd,
            onCheckedChange = { chkd = it },
            modifier = Modifier.padding(10.dp)
        )
    }
}
